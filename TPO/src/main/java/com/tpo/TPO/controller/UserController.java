package com.tpo.TPO.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tpo.TPO.entity.User;
import com.tpo.TPO.service.UserService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "Endpoints relacionados con la gestión de usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Obtener todos los usuarios", description = "Retorna una lista de usuarios con filtros de paginación y búsqueda.")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(
            @Parameter(description = "Parte del nombre de usuario a buscar") @RequestParam String contains,
            @Parameter(description = "Número de usuarios a saltar para la paginación") @RequestParam int skip,
            @Parameter(description = "Número máximo de usuarios a retornar") @RequestParam int limit,
            @Parameter(description = "Campo por el cual ordenar la lista de usuarios, opcional") @RequestParam(required = false) String orderby) {
        List<User> users = userService.getAllUsers(contains, skip, limit, orderby);
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Obtener usuario por ID", description = "Retorna un usuario específico por su ID.")
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@Parameter(description = "ID del usuario a buscar") @PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario en el sistema.")
    @PostMapping
    public ResponseEntity<User> createUser(@Parameter(description = "Datos del usuario a crear") @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(summary = "Actualizar un usuario", description = "Actualiza la información de un usuario existente.")
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(
            @Parameter(description = "ID del usuario a actualizar") @PathVariable Integer userId,
            @Parameter(description = "Datos actualizados del usuario") @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario por su ID.")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@Parameter(description = "ID del usuario a eliminar") @PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Obtener el número de comentarios de un usuario", description = "Retorna la cantidad de comentarios hechos por un usuario específico.")
    @GetMapping("/{userId}/comments")
    public ResponseEntity<Integer> getUserComments(@Parameter(description = "ID del usuario a consultar") @PathVariable Integer userId) {
        int commentsCount = userService.getUserCommentsCount(userId);
        return ResponseEntity.ok(commentsCount);
    }

    @Operation(summary = "Obtener seguidores de un usuario", description = "Retorna la lista de seguidores de un usuario específico.")
    @GetMapping("/{userId}/followers")
    public ResponseEntity<Set<User>> getFollowers(@Parameter(description = "ID del usuario a consultar") @PathVariable Integer userId) {
        Set<User> followers = userService.getFollowers(userId);
        if (followers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(followers);
    }

    @Operation(summary = "Obtener usuarios seguidos por un usuario", description = "Retorna la lista de usuarios seguidos por un usuario específico.")
    @GetMapping("/{userId}/followed")
    public ResponseEntity<Set<User>> getFollowed(@Parameter(description = "ID del usuario a consultar") @PathVariable Integer userId) {
        Set<User> followed = userService.getFollowed(userId);
        if (followed.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(followed);
    }

    @Operation(summary = "Seguir a un usuario", description = "Permite que un usuario siga a otro.")
    @PostMapping("/{userId}/follow/{followUserId}")
    public ResponseEntity<User> followUser(
            @Parameter(description = "ID del usuario que va a seguir a otro") @PathVariable Integer userId,
            @Parameter(description = "ID del usuario a seguir") @PathVariable Integer followUserId) {
        try {
            User followedUser = userService.followUser(userId, followUserId);
            return ResponseEntity.status(HttpStatus.CREATED).body(followedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // Usuario ya seguido
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Usuario no encontrado
        }
    }

    @Operation(summary = "Obtener usuario por correo electrónico", description = "Retorna un usuario basado en su correo electrónico.")
    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@Parameter(description = "Correo electrónico del usuario a buscar") @RequestParam String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Verificar si el correo electrónico está en uso", description = "Verifica si un correo electrónico ya está registrado en el sistema.")
    @GetMapping("/isEmailUsed")
    public ResponseEntity<Boolean> isEmailUsed(@Parameter(description = "Correo electrónico a verificar") @RequestParam String email) {
        boolean exists = userService.isEmailUsed(email);
        return ResponseEntity.ok(exists);
    }

    @Operation(summary = "Verificar si el nombre de usuario está en uso", description = "Verifica si un nombre de usuario ya está registrado en el sistema.")
    @GetMapping("/isUsernameUsed")
    public ResponseEntity<Boolean> isUsernameUsed(@Parameter(description = "Nombre de usuario a verificar") @RequestParam String username) {
        boolean exists = userService.isUsernameUsed(username);
        return ResponseEntity.ok(exists);
    }
}
