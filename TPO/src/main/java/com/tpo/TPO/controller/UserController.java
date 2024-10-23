package com.tpo.TPO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tpo.TPO.entity.User;
import com.tpo.TPO.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam String contains,
            @RequestParam int skip,
            @RequestParam int limit,
            @RequestParam(required = false) String orderby) {
        List<User> users = userService.getAllUsers(contains, skip, limit, orderby);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Integer userId, @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{userId}/comments")
    public ResponseEntity<Integer> getUserComments(@PathVariable Integer userId) {
        int commentsCount = userService.getUserCommentsCount(userId);
        return ResponseEntity.ok(commentsCount);
    }

    // Métodos para posts, followers, following, etc. se pueden agregar aquí
}
