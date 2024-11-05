package com.tpo.TPO.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tpo.TPO.service.PasswordResetService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pass")
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    // Endpoint para solicitar un restablecimiento de contraseña
    @PostMapping("/request-reset")
    public ResponseEntity<Void> requestPasswordReset(@RequestParam String email) {
        passwordResetService.requestPasswordReset(email);
        return ResponseEntity.ok().build(); // Respondemos con 200 OK
    }

    // Endpoint para verificar el TOTP
    @PostMapping("/verify-totp")
    public ResponseEntity<Map<String, Boolean>> verifyTotp(@RequestParam String email, @RequestParam String totpCode) {
        // Agregar registro para depuración
        System.out.println("Email: " + email + ", TOTP Code: " + totpCode);
        
        boolean isValid = passwordResetService.verifyTOTP(email, totpCode);
        
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", isValid);     
        return ResponseEntity.ok(response);
    }
    
}
