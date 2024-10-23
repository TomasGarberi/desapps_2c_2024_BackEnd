package com.tpo.TPO.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Ad {

    public Ad() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aId;

    @Column
    private String image;

    @Column
    private String url;

    // Puedes agregar más relaciones si es necesario
}
