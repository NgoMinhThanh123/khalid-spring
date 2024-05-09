package com.nmt.universitysb.controller;

import com.nmt.universitysb.dto.FirebaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/firebase")
@CrossOrigin
public class ApiFirebaseController {
    @Autowired
    private FirebaseProperties firebaseProperties;

    @GetMapping("/config")
    public FirebaseProperties getFirebaseConfig() {
        return firebaseProperties;
    }
}
