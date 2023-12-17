package com.example.AuthService.configs;


import com.example.AuthService.models.Role;
import com.example.AuthService.models.User;
import com.example.AuthService.repositories.UserRepository;
import com.example.AuthService.services.jwt.JwtFilter;
import com.example.AuthService.services.userDetails.UserDetailsServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;

import java.util.List;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthConfig {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().cors().disable()
                .addFilterAfter(jwtFilter, CsrfFilter.class)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                                .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                                .requestMatchers(HttpMethod.GET, "/user/*").permitAll()
                                .anyRequest().authenticated()
                );

        return http.build();
    }

//    @PostConstruct
//    public void initAdmin(){
//        var admin = userRepository.findByUsername("admin");
//        if (admin == null) {
//            User user = new User();
//            user.setId(0L);
//            user.setUsername("admin");
//            user.setPassword(passwordEncoder().encode("admin"));
//            user.setRoles(List.of(Role.EMPLOYEE, Role.ADMIN, Role.USER));
//            userRepository.save(user);
//        }
//    }
}
