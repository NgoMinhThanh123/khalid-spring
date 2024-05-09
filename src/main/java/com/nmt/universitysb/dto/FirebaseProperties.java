package com.nmt.universitysb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FirebaseProperties {
    private String apiKey;
    private String authDomain;
    private String projectId;
    private String storageBucket;
    private String messagingSenderId;
    private String appId;
}
