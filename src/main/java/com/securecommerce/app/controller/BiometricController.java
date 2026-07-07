package com.securecommerce.app.controller;

import com.securecommerce.app.service.BiometricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/biometric")
public class BiometricController {

    @Autowired
    private BiometricService biometricService;

    @PostMapping("/verify")
    public String verify(@RequestParam String email) {
        return biometricService.verifyBiometric(email);
    }
}