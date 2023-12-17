package com.example.SpringPostgres.services;


import com.example.SpringPostgres.entities.Book;
import com.example.SpringPostgres.entities.Telephone;
import com.example.SpringPostgres.repository.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelephoneService {
    @Autowired
    TelephoneRepository repository;
    public String readAll(){
        StringBuilder result = new StringBuilder();
        List<Telephone> productList = repository.findAll();
        for(Telephone book: productList){
            result.append(book.toString()).append("\n");
        }
        return result.toString();
    }
    public void updateAmount(Long id, int amount){
        repository.getReferenceById(id).setAmount(amount);
        repository.saveAndFlush(repository.getReferenceById(id));
    }
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
