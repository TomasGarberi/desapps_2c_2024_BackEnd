package com.tpo.TPO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tpo.TPO.entity.User;
import com.tpo.TPO.service.UserService;
<<<<<<< Updated upstream
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
=======
import lombok.extern.slf4j.Slf4j;
>>>>>>> Stashed changes

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/users")
<<<<<<< Updated upstream
@Tag(name = "User Controller", description = "User management operations") // Etiqueta general para el controlador
=======
@Tag(name = "User Controller", description = "Endpoints relacionados con la gestión de usuarios")
@Slf4j
>>>>>>> Stashed changes
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users", description = "Retrieve all users with optional filters")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(
            @Parameter(description = "Search users containing this string") @RequestParam String contains,
            @Parameter(description = "Number of records to skip") @RequestParam int skip,
            @Parameter(description = "Maximum number of records to return") @RequestParam int limit,
            @Parameter(description = "Field to sort by", required = false) @RequestParam(required = false) String orderby) {
        List<User> users = userService.getAllUsers(contains, skip, limit, orderby);
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Get a user by ID", description = "Retrieve a single user by their ID")
    @GetMapping("/{userId}")
<<<<<<< Updated upstream
    public ResponseEntity<User> getUserById(
            @Parameter(description = "ID of the user to retrieve") @PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
=======
    public ResponseEntity<User> getUserById(@Parameter(description = "ID del usuario a buscar") @PathVariable Integer userId) {
        return userService.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
>>>>>>> Stashed changes
    }

    @Operation(summary = "Create a new user", description = "Add a new user to the system")
    @PostMapping
    public ResponseEntity<User> createUser(
            @Parameter(description = "User object to be created") @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(summary = "Update an existing user", description = "Modify the details of an existing user by their ID")
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(
<<<<<<< Updated upstream
            @Parameter(description = "ID of the user to update") @PathVariable Integer userId,
            @Parameter(description = "Updated user object") @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
=======
            @Parameter(description = "ID del usuario a actualizar") @PathVariable Integer userId,
            @Parameter(description = "Datos actualizados del usuario") @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(userId, user);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
>>>>>>> Stashed changes
    }

    @Operation(summary = "Delete a user", description = "Remove a user by their ID")
    @DeleteMapping("/{userId}")
<<<<<<< Updated upstream
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID of the user to delete") @PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
=======
    public ResponseEntity<Void> deleteUser(@Parameter(description = "ID del usuario a eliminar") @PathVariable Integer userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
>>>>>>> Stashed changes
    }

    @Operation(summary = "Get user's comments count", description = "Retrieve the total count of comments for a user")
    @GetMapping("/{userId}/comments")
    public ResponseEntity<Integer> getUserComments(
            @Parameter(description = "ID of the user") @PathVariable Integer userId) {
        int commentsCount = userService.getUserCommentsCount(userId);
        return ResponseEntity.ok(commentsCount);
    }

    @Operation(summary = "Get user's followers", description = "Retrieve a list of users who follow the specified user")
    @GetMapping("/{userId}/followers")
    public ResponseEntity<Set<User>> getFollowers(
            @Parameter(description = "ID of the user") @PathVariable Integer userId) {
        Set<User> followers = userService.getFollowers(userId);
        if (followers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(followers);
    }

    @Operation(summary = "Get users followed by a user", description = "Retrieve a list of users followed by the specified user")
    @GetMapping("/{userId}/followed")
    public ResponseEntity<Set<User>> getFollowed(
            @Parameter(description = "ID of the user") @PathVariable Integer userId) {
        Set<User> followed = userService.getFollowed(userId);
        if (followed.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(followed);
    }

    @Operation(summary = "Follow another user", description = "Allows the specified user to follow another user by ID")
    @PostMapping("/{userId}/follow/{followUserId}")
    public ResponseEntity<User> followUser(
<<<<<<< Updated upstream
            @Parameter(description = "ID of the user initiating the follow") @PathVariable Integer userId,
            @Parameter(description = "ID of the user to be followed") @PathVariable Integer followUserId) {
=======
            @Parameter(description = "ID del usuario que va a seguir a otro") @PathVariable Integer userId,
            @Parameter(description = "ID del usuario a seguir") @PathVariable Integer followUserId) {
        log.info("Attempting to follow user with ID: {} by user with ID: {}", followUserId, userId);
>>>>>>> Stashed changes
        try {
            User followedUser = userService.followUser(userId, followUserId);
            log.info("User with ID: {} successfully followed user with ID: {}", userId, followUserId);
            return ResponseEntity.status(HttpStatus.CREATED).body(followedUser);
        } catch (IllegalArgumentException e) {
            log.warn("User with ID: {} is already following user with ID: {}", userId, followUserId);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // Usuario ya seguido
        } catch (RuntimeException e) {
            log.error("User with ID: {} or user to follow with ID: {} not found", userId, followUserId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Usuario no encontrado
        } catch (Exception e) {
            log.error("An unexpected error occurred while user with ID: {} was attempting to follow user with ID: {}", userId, followUserId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Error genérico
        }
    }
<<<<<<< Updated upstream
=======

    @Operation(summary = "Obtener usuario por correo electrónico", description = "Retorna un usuario basado en su correo electrónico.")
    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@Parameter(description = "Correo electrónico del usuario a buscar") @RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
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
>>>>>>> Stashed changes
}
