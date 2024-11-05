package com.tpo.TPO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.tpo.TPO.controller.auth.AuthenticationRequest;
import com.tpo.TPO.entity.User;
import com.tpo.TPO.repository.UserRepository;
import com.tpo.TPO.service.PasswordResetService;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pass")
@RequiredArgsConstructor
public class PasswordResetController {

    @Autowired
    private final UserRepository repository;
    @Autowired
    private final PasswordResetService passwordResetService;

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

    @PutMapping("/change-password")
    public ResponseEntity<Boolean> changePassword(@RequestBody AuthenticationRequest changedUser) {

        String usernameString = changedUser.getUsername();
        String password = changedUser.getPassword();

        // Buscar el usuario en la base de datos
        Optional<User> user = repository.findByUsername(usernameString);

        // Verificar si el usuario existe
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        User userobj = user.get();

        userobj.setPassword(password);

        User updatedUser = passwordResetService.resetUserPassword(userobj, password);

        if (updatedUser != null) {
            return ResponseEntity.ok(true);

        } else {
            return ResponseEntity.ok(false);
        }

    }
}
