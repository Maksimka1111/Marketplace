package com.example.SpringPostgres.controllers;

import com.example.SpringPostgres.entities.products.Telephone;
import com.example.SpringPostgres.services.TelephoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telephone")
public class TelephoneController {
    @Autowired
    TelephoneService service;

    @GetMapping("/read")
    public String readTelephone(@RequestParam long id){
        return service.read(id).toString();
    }
    @PostMapping("/create")
    public String createTelephone(@RequestParam String producer, int volume, int sellerNum, String type, int cost, String name){
        Telephone telephone = new Telephone();
        telephone.setCost(cost);
        telephone.setTypeOf(type);
        telephone.setName(name);
        telephone.setSellerNum(sellerNum);
        telephone.setVolume(volume);
        telephone.setProducer(producer);
        service.create(telephone);
        return "status: ok";
    }
    @PutMapping("/update")
    public String updateClient(@RequestParam long id, String producer, int volume, int sellerNum, String type, int cost, String name){
        Telephone telephone = new Telephone();
        telephone.setId(id);
        telephone.setCost(cost);
        telephone.setTypeOf(type);
        telephone.setName(name);
        telephone.setSellerNum(sellerNum);
        telephone.setVolume(volume);
        telephone.setProducer(producer);
        service.update(telephone);
        return "status: ok";
    }
    @DeleteMapping("/delete")
    public String deleteTelephone(@RequestParam long id){
        service.delete(id);
        return "status: ok";
    }
}