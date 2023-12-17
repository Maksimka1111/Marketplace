package com.example.AuthService.services;

import com.example.AuthService.models.Role;
import com.example.AuthService.models.User;
import com.example.AuthService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User getUserByNameAndPassword(String name, String password){
//        for(var a: userRepository.findAll()){
//            System.out.println(a.toString());
//        }
        User user = userRepository.findByUsername(name);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username");
        }
        else if (!user.getPassword().equals(password)){
            throw new UsernameNotFoundException("Invalid password");
        }
        return user;
    }

    @Override
    public Long nextUserId() {
        AtomicReference<Long> id = new AtomicReference<>(0L);
        var users = userRepository.findAll();
        users.forEach(user -> {
            if (user.getId() > id.get())
                id.set(user.getId());
        });
        id.set(id.get() + 1);
        return id.get();
    }

    @Override
    public void addRole(Long id, Role role) {
        var user = userRepository.findById(id);
        user.ifPresent(value -> value.getRoles().add(role));
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public User getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName());
    }

}
