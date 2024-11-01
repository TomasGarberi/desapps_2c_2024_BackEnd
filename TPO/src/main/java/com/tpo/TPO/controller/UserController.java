package com.tpo.TPO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tpo.TPO.entity.User;
import com.tpo.TPO.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "User management operations") // Etiqueta general para el controlador
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
    public ResponseEntity<User> getUserById(
            @Parameter(description = "ID of the user to retrieve") @PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
            @Parameter(description = "ID of the user to update") @PathVariable Integer userId,
            @Parameter(description = "Updated user object") @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @Operation(summary = "Delete a user", description = "Remove a user by their ID")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID of the user to delete") @PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
            @Parameter(description = "ID of the user initiating the follow") @PathVariable Integer userId,
            @Parameter(description = "ID of the user to be followed") @PathVariable Integer followUserId) {
        try {
            User followedUser = userService.followUser(userId, followUserId);
            return ResponseEntity.status(HttpStatus.CREATED).body(followedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // Usuario ya seguido
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Usuario no encontrado
        }
    }
}
