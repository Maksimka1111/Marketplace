package com.example.AuthService.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class JwtTarget {
    private Long id;
    private String username;
    private String token;
    private List<Role> roles;
}
