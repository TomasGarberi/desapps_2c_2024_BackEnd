package com.tpo.TPO.controller;

import com.tpo.TPO.entity.Ad;
import com.tpo.TPO.service.AdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ads")
@Tag(name = "Ad Controller", description = "Endpoints relacionados con la gestión de anuncios")
public class AdController {

    private final AdService adService;

    @Autowired
    public AdController(AdService adService) {
        this.adService = adService;
    }

    @Operation(summary = "Obtener todos los anuncios", description = "Retorna una lista de todos los anuncios disponibles.")
    @GetMapping
    public ResponseEntity<List<Ad>> getAllAds() {
        List<Ad> ads = adService.findAll();
        return ResponseEntity.ok(ads);
    }

    @Operation(summary = "Obtener un anuncio por ID", description = "Retorna un anuncio específico por su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Ad> getAdById(@Parameter(description = "ID del anuncio a buscar") @PathVariable("id") Integer id) {
        return adService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener un mensaje simple", description = "Retorna un mensaje de prueba.")
    @GetMapping("/message")
    public String getMessage() {
        return "good work!";
    }

    @Operation(summary = "Enviar un mensaje simple", description = "Retorna un mensaje de prueba mediante una solicitud POST.")
    @PostMapping("/message")
    public String getMessagepost() {
        return "good work!";
    }

    @Operation(summary = "Crear un nuevo anuncio", description = "Crea un nuevo anuncio en el sistema.")
    @PostMapping
    public ResponseEntity<Ad> createAd(@Parameter(description = "Datos del anuncio a crear") @RequestBody Ad ad) {
        Ad createdAd = adService.save(ad);
        return ResponseEntity.status(201).body(createdAd);
    }

    @Operation(summary = "Actualizar un anuncio", description = "Actualiza la información de un anuncio existente.")
    @PutMapping("/{id}")
    public ResponseEntity<Ad> updateAd(
            @Parameter(description = "ID del anuncio a actualizar") @PathVariable("id") Integer id,
            @Parameter(description = "Datos del anuncio a actualizar") @RequestBody Ad ad) {
        ad.setAId(id);
        Ad updatedAd = adService.save(ad);
        return ResponseEntity.ok(updatedAd);
    }

    @Operation(summary = "Eliminar un anuncio por ID", description = "Elimina un anuncio específico por su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@Parameter(description = "ID del anuncio a eliminar") @PathVariable("id") Integer id) {
        adService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
