package com.example.SpringPostgres.services;


import com.example.SpringPostgres.entities.Book;
import com.example.SpringPostgres.entities.WashingMachine;
import com.example.SpringPostgres.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService {

    @Autowired
    MachineRepository repository;
    public String readAll(){
        StringBuilder result = new StringBuilder();
        List<WashingMachine> productList = repository.findAll();
        for(WashingMachine book: productList){
            result.append(book.toString()).append("\n");
        }
        return result.toString();
    }
    public void updateAmount(Long id, int amount){
        repository.getReferenceById(id).setAmount(amount);
        repository.saveAndFlush(repository.getReferenceById(id));
    }
    public void create(WashingMachine machine){
        repository.save(machine);
    };
    public WashingMachine read(long id){
        if (repository.findById(id).isPresent())
            return repository.findById(id).get();
        else return null;
    }
    public void update(WashingMachine machine){
        repository.saveAndFlush(machine);
    }
    public void delete(long id){
        if (repository.findById(id).isPresent())
            repository.delete(repository.findById(id).get());
    }
}
