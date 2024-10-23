package com.tpo.TPO.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Post {

    public Post() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private Integer userId;

    // Puedes agregar más relaciones si es necesario
}
