package com.example.AuthService.controllers;


import com.example.AuthService.models.Role;
import com.example.AuthService.models.User;
import com.example.AuthService.services.jwt.JwtGenerator;
import com.example.AuthService.services.UserService;
import com.example.AuthService.services.userDetails.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@ComponentScan("com.example.AuthService.services")
public class UserController {
    private UserService userService;
    private UserDetailsServiceImpl userDetailsService;
    private JwtGenerator jwtGenerator;
    @Autowired
    public UserController(UserService userService, JwtGenerator jwtGenerator, UserDetailsServiceImpl userDetailsService){
        this.userService=userService;
        this.jwtGenerator=jwtGenerator;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/becameSeller")
    @PreAuthorize("hasAnyRole('USER', 'ADMINISTRATOR')")
    public ResponseEntity<?> wannaBeSeller(){
        return new ResponseEntity<>(jwtGenerator.addSellerRole(userService.getCurrentUser()), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String username, String password){
        User user = new User();
        user.setId(userService.nextUserId());
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(List.of(Role.USER));
        try{
            userDetailsService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(user, HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String username, String password) {
        User user = userService.getUserByNameAndPassword(username, password);
        try {
            if(user.getUsername() == null || user.getPassword() == null) {
                throw new UsernameNotFoundException("UserName or Password is Empty");
            }
            User userData = userService.getUserByNameAndPassword(user.getUsername(), user.getPassword());
            if(userData == null){
                throw new UsernameNotFoundException("UserName or Password is Invalid");
            }
            return new ResponseEntity<>(jwtGenerator.generateToken(user), HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('USER', 'SELLER', 'ADMIN')")
    public ResponseEntity<?> getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(auth.getAuthorities());
    }

    @GetMapping("/logout")
    @PreAuthorize("hasAnyRole('USER', 'SELLER', 'ADMIN')")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        jwtGenerator.clearJson();
        return ResponseEntity.ok("Your token has been reset");
    }
}
