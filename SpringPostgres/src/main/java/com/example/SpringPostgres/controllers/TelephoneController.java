package com.example.SpringPostgres.controllers;

import com.example.SpringPostgres.entities.Telephone;
import com.example.SpringPostgres.services.ClientService;
import com.example.SpringPostgres.services.TelephoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telephone")
public class TelephoneController {
    @Autowired
    TelephoneService service;
    @Autowired
    ClientService clientService;
    @GetMapping("/readAll")
    @PreAuthorize("hasAnyRole('USER', 'SELLER', 'ADMIN')")
    public String readAllBook(){
        return service.readAll();
    }
    @GetMapping("/read")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    public String readTelephone(@RequestParam long id){
        return service.read(id).toString();
    }
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    public String createTelephone(@RequestParam String producer, int volume, String type, int cost, String name, int amount){
        Telephone telephone = new Telephone();
        telephone.setCost(cost);
        telephone.setTypeOf(type);
        telephone.setName(name);
        telephone.setAmount(amount);
        telephone.setSellerId(clientService.getCurrentUser().getId());
        telephone.setVolume(volume);
        telephone.setProducer(producer);
        service.create(telephone);
        return "status: ok";
    }
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    public String updateClient(@RequestParam long id, String producer, int volume, int sellerNum, String type, int cost, String name, int amount){
        Telephone telephone = new Telephone();
        telephone.setId(id);
        telephone.setCost(cost);
        telephone.setTypeOf(type);
        telephone.setName(name);
        telephone.setVolume(volume);
        telephone.setProducer(producer);
        telephone.setAmount(amount);
        service.update(telephone);
        return "status: ok";
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    public String deleteTelephone(@RequestParam long id){
        service.delete(id);
        return "status: ok";
    }
}