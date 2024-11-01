package com.tpo.TPO.controller;

import com.tpo.TPO.entity.Comment;
import com.tpo.TPO.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/posts/{postId}/comments")
@Tag(name = "Comment Controller", description = "Operations for managing comments on posts") // Swagger Tag para el controlador
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Get comments for a specific post", description = "Retrieve all comments for the given post ID")
    @GetMapping
    public ResponseEntity<List<Comment>> getCommentsPostID(
            @Parameter(description = "ID of the post to retrieve comments for") @PathVariable Integer postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        if (comments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(comments);
    }

    @Operation(summary = "Add a new comment to a post", description = "Post a new comment under a specific post ID")
    @PostMapping
    public ResponseEntity<Comment> postCommentsPostID(
            @Parameter(description = "ID of the post to add the comment to") @PathVariable Integer postId,
            @Parameter(description = "Comment object to be added") @RequestBody Comment comment) {
        Comment createdComment = commentService.createComment(postId, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @Operation(summary = "Delete a comment from a post", description = "Delete a specific comment by ID from a given post")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @Parameter(description = "ID of the post") @PathVariable Integer postId,
            @Parameter(description = "ID of the comment to delete") @PathVariable Integer commentId) {
        boolean deleted = commentService.deleteComment(postId, commentId);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().build();
    }
}
