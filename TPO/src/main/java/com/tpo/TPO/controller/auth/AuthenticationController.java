package com.tpo.TPO.controller.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tpo.TPO.controller.dto.JwtResponse;
import com.tpo.TPO.controller.dto.RefreshTokenRequest;
import com.tpo.TPO.entity.RefreshToken;
import com.tpo.TPO.repository.UserRepository;
import com.tpo.TPO.service.AuthenticationService;
import com.tpo.TPO.service.RefreshTokenService;
import com.tpo.TPO.service.UserService;
import com.tpo.TPO.controller.config.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @Autowired
    private UserService userService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtservice;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request) {

        Optional<com.tpo.TPO.entity.User> user = userRepository.findByUsername(request.getUsername());
        Optional<com.tpo.TPO.entity.User> usermail = userRepository.findByEmail(request.getEmail());

        if (user.isEmpty()) {

            if (usermail.isEmpty()) {
                return ResponseEntity.ok(service.register(request));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mail existente");

            }

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario existente");
        }

    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(
            @RequestBody AuthenticationRequest request) {

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.getUsername());
        return JwtResponse.builder()
                .accessToken(service.authenticate(request))
                .token(refreshToken.getToken())
                .build();

    }
/*
 *  @PostMapping("/refreshToken")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return refreshTokenService.findByToken(refreshTokenRequest.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(User -> {
                    String accessToken = jwtservice.generateToken(User, User.getId());
                    return JwtResponse.builder().accessToken(accessToken).token(refreshTokenRequest.getToken())
                            .build();

                }).orElseThrow(() -> new RuntimeException("Token not in DB"));

    }
 */

}