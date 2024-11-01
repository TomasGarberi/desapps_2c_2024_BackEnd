package com.tpo.TPO.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tpo.TPO.service.PasswordResetService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pass")
@Tag(name = "Password Reset Controller", description = "Operations for password reset") // Swagger Tag para el controlador
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @Operation(summary = "Request a password reset", description = "Initiate a password reset process for the specified email")
    @PostMapping("/request-reset")
    public ResponseEntity<Void> requestPasswordReset(
            @Parameter(description = "Email address for which to request a password reset") @RequestParam String email) {
        passwordResetService.requestPasswordReset(email);
        return ResponseEntity.ok().build(); // Respondemos con 200 OK
    }

    @Operation(summary = "Verify TOTP code", description = "Verify the TOTP code sent to the email")
    @PostMapping("/verify-totp")
    public ResponseEntity<Map<String, Boolean>> verifyTotp(
            @Parameter(description = "Email address associated with the TOTP") @RequestParam String email,
            @Parameter(description = "TOTP code to verify") @RequestParam String totpCode) {
        boolean isValid = passwordResetService.verifyTOTP(email, totpCode);

        Map<String, Boolean> response = new HashMap<>();
        response.put("success", isValid);

        return ResponseEntity.ok(response); // Respondemos con el resultado de la verificación
    }
}
