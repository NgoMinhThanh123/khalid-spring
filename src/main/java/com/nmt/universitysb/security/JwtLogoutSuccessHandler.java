package com.nmt.universitysb.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import java.io.IOException;

public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        SecurityContextHolder.clearContext();
        response.setStatus(HttpServletResponse.SC_OK);
    }

//    @Override
//    public void onLogoutSuccess(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        response.setStatus(HttpServletResponse.SC_OK);
//    }
}
