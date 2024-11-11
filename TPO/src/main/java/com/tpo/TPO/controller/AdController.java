package com.tpo.TPO.controller;

<<<<<<< Updated upstream
=======
import com.tpo.TPO.service.AdApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
<<<<<<< Updated upstream

import com.tpo.TPO.entity.Ad;
import com.tpo.TPO.service.AdService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
=======
import org.springframework.web.client.RestClientException;
>>>>>>> Stashed changes

@RestController
@RequestMapping("/ads")
@Tag(name = "Ad Controller", description = "Controller for managing advertisements") // Descripción del controlador en Swagger
public class AdController {

    private final AdApiService adApiService;

    @Autowired
    public AdController(AdApiService adApiService) {
        this.adApiService = adApiService;
    }

    @Operation(summary = "Get all ads", description = "Retrieves a list of all ads available")
    @GetMapping
<<<<<<< Updated upstream
    public ResponseEntity<List<Ad>> getAllAds() {
        List<Ad> ads = adService.findAll();
        return ResponseEntity.ok(ads);
    }

    @Operation(summary = "Get ad by ID", description = "Retrieve a single ad by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Ad> getAdById(
            @Parameter(description = "ID of the ad to be retrieved") @PathVariable("id") Integer id) {
        return adService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get test message", description = "Returns a test message for verification purposes")
    @GetMapping("/message")
    public String getMessage() {
        return "good work!";
    }

    @Operation(summary = "Post test message", description = "Accepts a POST request and returns a test message")
    @PostMapping("/message")
    public String getMessagepost() {
        return "good work!";
    }

    @Operation(summary = "Create a new ad", description = "Adds a new ad to the system")
    @PostMapping
    public ResponseEntity<Ad> createAd(
            @Parameter(description = "Ad object that needs to be created") @RequestBody Ad ad) {
        Ad createdAd = adService.save(ad);
        return ResponseEntity.status(201).body(createdAd);
    }

    @Operation(summary = "Update an existing ad", description = "Updates an ad by its ID")
    @PutMapping("/{id}")
    public ResponseEntity<Ad> updateAd(
            @Parameter(description = "ID of the ad to be updated") @PathVariable("id") Integer id,
            @Parameter(description = "Updated ad object") @RequestBody Ad ad) {
        ad.setAId(id);
        Ad updatedAd = adService.save(ad);
        return ResponseEntity.ok(updatedAd);
    }

    @Operation(summary = "Delete an ad", description = "Deletes an ad by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(
            @Parameter(description = "ID of the ad to be deleted") @PathVariable("id") Integer id) {
        adService.deleteById(id);
        return ResponseEntity.noContent().build();
=======
    public ResponseEntity<Object> getAllAds() {
        try {
            ResponseEntity<Object> response = adApiService.getAdsFromExternalApi();
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Error al obtener los anuncios externos: " + e.getMessage());
        }
>>>>>>> Stashed changes
    }
}
