package com.tpo.TPO.controller;

import com.tpo.TPO.controller.auth.AuthenticationRequest;
import com.tpo.TPO.entity.User;
import com.tpo.TPO.repository.UserRepository;
import com.tpo.TPO.service.PasswordResetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pass")
@Tag(name = "Password Reset Controller", description = "Endpoints relacionados con el restablecimiento de contraseñas de usuarios")
@RequiredArgsConstructor
public class PasswordResetController {

    @Autowired
    private final UserRepository repository;
    @Autowired
    private final PasswordResetService passwordResetService;

    @Operation(summary = "Solicitar restablecimiento de contraseña", description = "Envía una solicitud de restablecimiento de contraseña al correo electrónico proporcionado.")
    @PostMapping("/request-reset")
    public ResponseEntity<Void> requestPasswordReset(@Parameter(description = "Correo electrónico del usuario que solicita el restablecimiento") @RequestParam String email) {
        passwordResetService.requestPasswordReset(email);
        return ResponseEntity.ok().build(); // Respondemos con 200 OK
    }

    @Operation(summary = "Verificar código TOTP", description = "Verifica si el código TOTP proporcionado es válido para el correo electrónico del usuario.")
    @PostMapping("/verify-totp")
    public ResponseEntity<Map<String, Boolean>> verifyTotp(
            @Parameter(description = "Correo electrónico del usuario que solicita la verificación") @RequestParam String email,
            @Parameter(description = "Código TOTP proporcionado por el usuario") @RequestParam String totpCode) {
        // Agregar registro para depuración
        System.out.println("Email: " + email + ", TOTP Code: " + totpCode);

        boolean isValid = passwordResetService.verifyTOTP(email, totpCode);

        Map<String, Boolean> response = new HashMap<>();
        response.put("success", isValid);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cambiar contraseña", description = "Permite a un usuario cambiar su contraseña después de una verificación exitosa.")
    @PutMapping("/change-password")
    public ResponseEntity<Boolean> changePassword(@Parameter(description = "Datos del usuario y nueva contraseña") @RequestBody AuthenticationRequest changedUser) {

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
