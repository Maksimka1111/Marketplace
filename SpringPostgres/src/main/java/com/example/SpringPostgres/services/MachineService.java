package com.example.SpringPostgres.services;


import com.example.SpringPostgres.entities.products.WashingMachine;
import com.example.SpringPostgres.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MachineService {

    @Autowired
    MachineRepository repository;

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
