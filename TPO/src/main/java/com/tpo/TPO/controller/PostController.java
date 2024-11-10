package com.tpo.TPO.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tpo.TPO.entity.Post;
import com.tpo.TPO.service.PostService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@Tag(name = "Post Controller", description = "Endpoints relacionados con la gestión de publicaciones")
public class PostController {

    @Autowired
    private PostService postService;

    @Operation(summary = "Obtener una publicación por ID", description = "Retorna una publicación específica por su ID.")
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@Parameter(description = "ID de la publicación a buscar") @PathVariable Integer postId) {
        Optional<Post> post = postService.getPostById(postId);
        return post.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Crear una nueva publicación", description = "Crea una nueva publicación en el sistema.")
    @PostMapping
    public ResponseEntity<Post> createPost(@Parameter(description = "Datos de la publicación a crear") @RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @Operation(summary = "Eliminar una publicación", description = "Elimina una publicación por su ID.")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@Parameter(description = "ID de la publicación a eliminar") @PathVariable Integer postId) {
        postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Obtener todas las publicaciones", description = "Retorna una lista de todas las publicaciones.")
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Obtener el timeline de un usuario", description = "Retorna una lista de publicaciones para el timeline de un usuario específico.")
    @GetMapping("/timeline/{userId}")
    public ResponseEntity<List<Post>> getTimeline(@Parameter(description = "ID del usuario para obtener su timeline") @PathVariable Integer userId) {
        List<Post> timelinePosts = postService.getTimeline(userId);
        if (timelinePosts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(timelinePosts);
    }

    @Operation(summary = "Obtener publicaciones de un usuario específico", description = "Retorna una lista de publicaciones hechas por un usuario específico.")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(@Parameter(description = "ID del usuario para obtener sus publicaciones") @PathVariable Integer userId) {
        List<Post> posts = postService.getPostsByUser(userId);
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts);
    }
}
