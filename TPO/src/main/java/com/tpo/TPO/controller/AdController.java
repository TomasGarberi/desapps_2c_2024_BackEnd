package com.tpo.TPO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tpo.TPO.entity.Ad;
import com.tpo.TPO.service.AdService;

import java.util.List;

@RestController
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;

    @Autowired
    public AdController(AdService adService) {
        this.adService = adService;
    }

    /**
     * Retrieves a list of ads.
     *
     * @return List of Ads
     */
    @GetMapping
    public ResponseEntity<List<Ad>> getAllAds() {
        List<Ad> ads = adService.findAll();
        return ResponseEntity.ok(ads);
    }

    /**
     * Retrieves an ad by its ID.
     *
     * @param id the ID of the ad
     * @return ResponseEntity containing the ad or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Ad> getAdById(@PathVariable("id") Integer id) {
        return adService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new ad.
     *
     * @param ad the ad to create
     * @return the created ad
     */
    @PostMapping
    public ResponseEntity<Ad> createAd(@RequestBody Ad ad) {
        Ad createdAd = adService.save(ad);
        return ResponseEntity.status(201).body(createdAd);
    }

    /**
     * Updates an existing ad.
     *
     * @param id the ID of the ad to update
     * @param ad the ad data to update
     * @return ResponseEntity containing the updated ad
     */
    @PutMapping("/{id}")
    public ResponseEntity<Ad> updateAd(@PathVariable("id") Integer id, @RequestBody Ad ad) {
        ad.setAId(id);
        Ad updatedAd = adService.save(ad);
        return ResponseEntity.ok(updatedAd);
    }

    /**
     * Deletes an ad by its ID.
     *
     * @param id the ID of the ad to delete
     * @return ResponseEntity indicating the result of the deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable("id") Integer id) {
        adService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
