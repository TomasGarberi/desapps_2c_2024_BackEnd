package com.tpo.TPO.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tpo.TPO.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    
    // Método para obtener todos los posts de un usuario específico
    @Query("SELECT p FROM Post p WHERE p.userId = :userId")
    List<Post> getPostsByUser(@Param("userId") Integer userId);
    
    // Método para obtener todos los posts de los usuarios seguidos por un usuario
    @Query("SELECT p FROM Post p WHERE p.userId IN (SELECT followed.id FROM User user JOIN user.followed followed WHERE user.id = :userId) ORDER BY fecha desc")
    List<Post> getTimeline(@Param("userId") Integer userId);
}