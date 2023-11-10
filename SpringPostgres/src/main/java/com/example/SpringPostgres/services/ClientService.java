package com.example.SpringPostgres.services;

import com.example.SpringPostgres.entities.Client;
import com.example.SpringPostgres.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

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
}
