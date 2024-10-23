package com.tpo.TPO.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {

    public User() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String name;

    @Column
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String urlImage;

    // Puedes agregar más relaciones si es necesario
}
