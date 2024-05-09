package com.nmt.universitysb.service;

import com.nmt.universitysb.dto.JwtResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    JwtResponse login(UserDetails userDetails);

}
