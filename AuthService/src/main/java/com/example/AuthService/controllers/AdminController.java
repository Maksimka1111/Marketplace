package com.example.AuthService.controllers;

import com.example.AuthService.models.Role;
import com.example.AuthService.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/del")
    public ResponseEntity<?> del(@RequestParam Long id){;
        userService.delete(id);
        return ResponseEntity.ok("status: ok");
    }
    @PostMapping("/addRole")
    public ResponseEntity<?> addRole(@RequestParam Long userId, String role){;
        if (!role.equals(Role.SELLER.name()))
            return ResponseEntity.ok("no such role");
        userService.addRole(userId, Role.valueOf(role));
        return ResponseEntity.ok("status: ok");
    }
}
