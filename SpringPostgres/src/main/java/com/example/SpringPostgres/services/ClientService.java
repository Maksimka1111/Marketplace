package com.example.SpringPostgres.services;

import com.example.SpringPostgres.entities.Client;
import com.example.SpringPostgres.entities.Order;
import com.example.SpringPostgres.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    public void addOrder(Order order){
        getCurrentUser().addOrder(order);
        repository.flush();
    }
    public void delOrder(Order order){
        getCurrentUser().delOrder(order);
        repository.flush();
    }
    public void create(Client client){
        repository.save(client);
    };
    public Client read(long id){
        return repository.findById(id).get();
    }
    public void update(Client client){
        repository.saveAndFlush(client);
    }
    public void delete(long id){
        if (repository.findById(id).isPresent())
            repository.delete(repository.findById(id).get());
    }
    public Client getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return repository.findByUsername(auth.getName());
    }
}
