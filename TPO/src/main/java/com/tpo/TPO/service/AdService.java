package com.tpo.TPO.service;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tpo.TPO.entity.Ad;
import com.tpo.TPO.repository.AdRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdService {

    private final AdRepository adRepository;

    public AdService(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    public Optional<Ad> findById(Integer aId) {
        return adRepository.findById(aId);
    }

    public Ad save(Ad ad) {
        return adRepository.save(ad);
    }

    public void deleteById(Integer aId) {
        adRepository.deleteById(aId);
    }
}
