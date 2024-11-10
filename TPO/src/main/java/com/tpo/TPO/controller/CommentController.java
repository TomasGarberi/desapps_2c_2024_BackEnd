package com.tpo.TPO.controller;

import com.tpo.TPO.entity.Comment;
import com.tpo.TPO.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/{postId}/comments")
@Tag(name = "Comment Controller", description = "Endpoints relacionados con la gestión de comentarios de publicaciones")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Obtener todos los comentarios de una publicación", description = "Retorna todos los comentarios asociados a una publicación específica.")
    @GetMapping
    public ResponseEntity<List<Comment>> getCommentsPostID(
            @Parameter(description = "ID de la publicación para obtener los comentarios") @PathVariable Integer postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        if (comments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(comments);
    }

    @Operation(summary = "Crear un nuevo comentario en una publicación", description = "Añade un nuevo comentario a una publicación específica.")
    @PostMapping
    public ResponseEntity<Comment> postCommentsPostID(
            @Parameter(description = "ID de la publicación donde se añadirá el comentario") @PathVariable Integer postId,
            @Parameter(description = "Datos del comentario a crear") @RequestBody Comment comment) {
        Comment createdComment = commentService.createComment(postId, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @Operation(summary = "Eliminar un comentario de una publicación", description = "Elimina un comentario específico asociado a una publicación.")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @Parameter(description = "ID de la publicación donde se encuentra el comentario") @PathVariable Integer postId,
            @Parameter(description = "ID del comentario a eliminar") @PathVariable Integer commentId) {
        boolean deleted = commentService.deleteComment(postId, commentId);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().build();
    }
}
