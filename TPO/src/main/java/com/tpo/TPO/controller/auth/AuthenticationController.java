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

import com.tpo.TPO.repository.UserRepository;
import com.tpo.TPO.service.AuthenticationService;
import com.tpo.TPO.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dai")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

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
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}