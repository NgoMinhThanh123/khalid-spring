package com.nmt.universitysb.config;

import com.nmt.universitysb.dto.FirebaseProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirebaseConfig {
    @Value("${firebase.apiKey}")
    private String apiKey;
    @Value("${firebase.authDomain}")
    private String authDomain;
    @Value("${firebase.projectId}")
    private String projectId;
    @Value("${firebase.storageBucket}")
    private String storageBucket;
    @Value("${firebase.messagingSenderId}")
    private String messagingSenderId;
    @Value("${firebase.appId}")
    private String appId;

    @Bean
    public FirebaseProperties firebaseProperties() {
        FirebaseProperties properties = new FirebaseProperties();
        properties.setApiKey(apiKey);
        properties.setAuthDomain(authDomain);
        properties.setProjectId(projectId);
        properties.setStorageBucket(storageBucket);
        properties.setMessagingSenderId(messagingSenderId);
        properties.setAppId(appId);
        return properties;
    }
}
