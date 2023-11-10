package com.example.SpringPostgres.services;


import com.example.SpringPostgres.entities.products.Telephone;
import com.example.SpringPostgres.repository.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelephoneService {
    @Autowired
    TelephoneRepository repository;

    public void create(Telephone telephone){
        repository.save(telephone);
    };
    public Telephone read(long id){
        if (repository.findById(id).isPresent())
            return repository.findById(id).get();
        else return null;
    }
    public void update(Telephone telephone){
        repository.saveAndFlush(telephone);
    }
    public void delete(long id){
        if (repository.findById(id).isPresent())
            repository.delete(repository.findById(id).get());
    }
}
