package com.tpo.TPO.controller;

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

    // Get User
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    // Post User
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    // Update User
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Integer userId, @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    // Delete User
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Get Comments
    @GetMapping("/{userId}/comments")
    public ResponseEntity<Integer> getUserComments(@PathVariable Integer userId) {
        int commentsCount = userService.getUserCommentsCount(userId);
        return ResponseEntity.ok(commentsCount);
    }

    // Get Followers
    @GetMapping("/{userId}/followers")
    public ResponseEntity<Set<User>> getFollowers(@PathVariable Integer userId) {
        Set<User> followers = userService.getFollowers(userId);
        if (followers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(followers);
    }

    // Get Followed
    @GetMapping("/{userId}/followed")
    public ResponseEntity<Set<User>> getFollowed(@PathVariable Integer userId) {
        Set<User> followed = userService.getFollowed(userId);
        if (followed.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(followed);
    }
}
