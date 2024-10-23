package com.tpo.TPO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tpo.TPO.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
