package com.tpo.TPO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tpo.TPO.entity.Post;
import com.tpo.TPO.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@Tag(name = "Post Controller", description = "Operations for managing posts") // Swagger Tag para el controlador
public class PostController {

    @Autowired
    private PostService postService;

    @Operation(summary = "Get post by ID", description = "Retrieve a single post by its ID") // Swagger Operation
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(
            @Parameter(description = "ID of the post to retrieve") @PathVariable Integer postId) { // Swagger Parameter
        Optional<Post> post = postService.getPostById(postId);
        return post.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Create a new post", description = "Create a new post with the given details")
    @PostMapping
    public ResponseEntity<Post> createPost(
            @Parameter(description = "Post object to be created") @RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @Operation(summary = "Delete post by ID", description = "Delete a post by its ID")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @Parameter(description = "ID of the post to delete") @PathVariable Integer postId) {
        postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Get all posts", description = "Retrieve a list of all posts")
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Get timeline for a user", description = "Retrieve the timeline posts of a specific user")
    @GetMapping("/timeline/{userId}")
    public ResponseEntity<List<Post>> getTimeline(
            @Parameter(description = "ID of the user to retrieve timeline for") @PathVariable Integer userId) {
        List<Post> timelinePosts = postService.getTimeline(userId);
        if (timelinePosts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(timelinePosts);
    }

    @Operation(summary = "Get posts by user", description = "Retrieve posts created by a specific user")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(
            @Parameter(description = "ID of the user to retrieve posts for") @PathVariable Integer userId) {
        List<Post> posts = postService.getPostsByUser(userId);
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts);
    }
}
