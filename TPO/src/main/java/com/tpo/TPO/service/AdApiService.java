package com.tpo.TPO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdApiService {

    private final RestTemplate restTemplate;

    @Autowired
    public AdApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Object> getAdsFromExternalApi() {
        String url = "https://my-json-server.typicode.com/chrismazzeo/advertising_da1/ads";
        return restTemplate.getForEntity(url, Object.class);
    }
}
