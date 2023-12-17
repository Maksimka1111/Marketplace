package com.example.AuthService.services.jwt;

import com.example.AuthService.models.JwtTarget;
import com.example.AuthService.models.User;

import java.util.Map;

public interface JwtGenerator {
    Map<String, String> generateToken(User user);
    Map<String, String> addSellerRole(User user);
    Map<String, String> validateToken(String token);
    void clearJson();
}
