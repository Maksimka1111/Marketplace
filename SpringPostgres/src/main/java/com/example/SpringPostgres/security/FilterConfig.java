package com.example.SpringPostgres.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class FilterConfig {
    @Autowired
    JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterAfter(jwtFilter, CsrfFilter.class)

                .authorizeHttpRequests(registry -> {
                    try {
                        registry.anyRequest().authenticated();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        return http.build();
    }
}