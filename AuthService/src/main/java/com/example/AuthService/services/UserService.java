package com.example.AuthService.services;

import com.example.AuthService.models.Role;
import com.example.AuthService.models.User;

public interface UserService {
    User getUserByNameAndPassword(String name, String password);
    Long nextUserId();
    void addRole(Long id, Role role);
    void delete(Long id);
    User getCurrentUser();
}
