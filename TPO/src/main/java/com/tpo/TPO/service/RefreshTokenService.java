package com.tpo.TPO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpo.TPO.entity.RefreshToken;
import com.tpo.TPO.repository.RefreshTokenRepository;
import com.tpo.TPO.repository.UserRepository;

import java.util.UUID;
import java.time.Instant;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userRepository.findByUsername(username).get())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusSeconds(345600)) // 1 mes de token
                .build();

        return refreshTokenRepository.save(refreshToken);

    }

}
