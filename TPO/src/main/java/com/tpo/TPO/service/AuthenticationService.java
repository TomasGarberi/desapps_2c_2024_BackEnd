package com.tpo.TPO.service;

import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tpo.TPO.controller.auth.AuthenticationRequest;
import com.tpo.TPO.controller.auth.AuthenticationResponse;
import com.tpo.TPO.controller.auth.RegisterRequest;
import com.tpo.TPO.controller.config.JwtService;
import com.tpo.TPO.entity.User;
import com.tpo.TPO.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
        private final UserRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {
                var usuario = User.builder()
                                .name(request.getName())
                                .email(request.getEmail())
                                .username(request.getUsername())
                                .lastName(request.getLastname())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .role(request.getRole())
                                .build();
                repository.save(usuario);

                var jwtToken = jwtService.generateToken(usuario, usuario.getId()); // aca el getusuario_id te da
                                                                                   // el id para crear en el
                                                                                   // token directo del usuario
                                                                                   // recientemente generado
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }

        public String authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getUsername(),
                                                request.getPassword()));

                var usuario = repository.findByUsername(request.getUsername())

                                .orElseThrow();
                var jwtToken = jwtService.generateToken(usuario, usuario.getId());
                return jwtToken;
        }
}
