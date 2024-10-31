package com.tpo.TPO.service;

import com.tpo.TPO.utils.EmailSender;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {

    private Map<String, String> resetTokens = new HashMap<>(); // Almacenar pares de email y código generado
    private Map<String, Long> tokenTimestamps = new HashMap<>(); // Almacenar tiempos de generación de TOTP
    private GoogleAuthenticator gAuth = new GoogleAuthenticator();

    // Generar un código TOTP de 4 dígitos para un usuario
    public String generateTOTP(String email) {
        int code = gAuth.getTotpPassword("userSecretKey");  // Idealmente, deberías usar una clave única para cada usuario
        String fourDigitCode = String.format("%04d", code % 10000);  // Reducir a 4 dígitos
        resetTokens.put(email, fourDigitCode);  // Almacenar el código TOTP junto con el email del usuario
        tokenTimestamps.put(email, System.currentTimeMillis()); // Almacenar el tiempo de generación
        return fourDigitCode;
    }

    // Método para enviar el correo (aquí puedes configurar la lógica de envío)
    public void sendEmail(String to, String subject, String body) {
        EmailSender.sendEmail(to, subject, body);  // Llamada al método de EmailSender
    }

    // Solicitar restablecimiento de contraseña generando TOTP y enviando un correo electrónico
    public void requestPasswordReset(String email) {
        String totpCode = generateTOTP(email); // Genera el TOTP
        String subject = "Password Reset Code";
        String body = "Your TOTP code for password reset is: " + totpCode + ". This code will expire in 5 minutes.";
        sendEmail(email, subject, body); // Envía el correo
    }
    

    // Verificar si el código TOTP ingresado es correcto
    public boolean verifyTOTP(String email, String inputCode) {
        String storedCode = resetTokens.get(email);
        long generatedTime = tokenTimestamps.get(email);
        long currentTime = System.currentTimeMillis();
        
        // Verificar si el código es correcto y si no ha expirado
        return storedCode != null && storedCode.equals(inputCode) && (currentTime - generatedTime < 300000); // 5 minutos en milisegundos
    }
}
