package com.example.AuthService.services.jwt;

import com.example.AuthService.models.JwtTarget;
import com.example.AuthService.models.Role;
import com.example.AuthService.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JwtGeneratorImpl implements JwtGenerator {
    private Map<String, String> json;
    @Override
    public Map<String, String> generateToken(User user) {
        if (json == null) {
            json = new HashMap<>();
            String token = "";
            token = Jwts.builder()
                    .setSubject(user.getUsername())
                    .claim("authorities", user.getRoles().toString())
                    .signWith(SignatureAlgorithm.HS256, "secret")
                    .compact();
            JwtTarget target = JwtTarget.builder()
                    .username(user.getUsername())
                    .id(user.getId())
                    .token(token)
                    .roles(user.getRoles())
                    .build();
            json.put("id", target.getId().toString());
            json.put("username", target.getUsername());
            json.put("roles", target.getRoles().toString());
            json.put("token", target.getToken());
        }
        return json;
    }

    @Override
    public Map<String, String> validateToken(String token) {
        if (json.get("token").equals(token)) {
            return json;
        }
        return null;
    }

    @Override
    public Map<String, String> addSellerRole(User user) {
        if (!json.isEmpty()) {
            String token = "";
            user.setRoles(List.of(Role.USER, Role.SELLER));
            token = Jwts.builder()
                    .setSubject(user.getUsername())
                    .claim("authorities", user.getRoles().toString())
                    .signWith(SignatureAlgorithm.HS256, "secret")
                    .compact();
            JwtTarget target = JwtTarget.builder()
                    .username(user.getUsername())
                    .id(user.getId())
                    .token(token)
                    .roles(user.getRoles())
                    .build();
            json.put("New_Token", "Use new token");
            json.put("id", target.getId().toString());
            json.put("username", target.getUsername());
            json.put("roles", target.getRoles().toString());
            json.put("token", target.getToken());
            return json;
        }
        return json;
    }

    @Override
    public void clearJson() {
        json.clear();
    }
}
