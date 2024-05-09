package com.nmt.universitysb.service.impl;
import com.nmt.universitysb.dto.JwtResponse;
import com.nmt.universitysb.security.JwtTokenProvider;
import com.nmt.universitysb.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Override
    public JwtResponse login(UserDetails userDetails) {
        JwtResponse jwtResponse = tokenProvider.generateToken(userDetails);
        return jwtResponse;
    }


}
