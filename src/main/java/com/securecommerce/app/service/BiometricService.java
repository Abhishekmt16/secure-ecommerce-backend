package com.securecommerce.app.service;

import com.securecommerce.app.dto.ApiResponse;
import com.securecommerce.app.dto.BiometricResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BiometricService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${ai.service.url}")
    private String aiServiceUrl;

    public String verifyBiometric(String email) {

        BiometricResponse response = restTemplate.postForObject(
                aiServiceUrl + "/biometric/verify",
                null,
                BiometricResponse.class
        );

        if (response != null && "VERIFIED".equals(response.getStatus())) {
            return "VERIFIED";
        }

        return "BIOMETRIC_FAILED";
    }
}