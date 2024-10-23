package com.tpo.TPO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tpo.TPO.entity.Ad;

public interface AdRepository extends JpaRepository<Ad, Integer> {
    // Puedes agregar métodos de consulta personalizados aquí si es necesario
}
