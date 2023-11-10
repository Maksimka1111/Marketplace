package com.example.SpringPostgres.controllers;

import com.example.SpringPostgres.entities.products.WashingMachine;
import com.example.SpringPostgres.services.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/machine")
public class MachineController {
    @Autowired
    MachineService service;

    @GetMapping("/read")
    public String readMachine(@RequestParam long id) {
        return service.read(id).toString();
    }

    @PostMapping("/create")
    public String createMachine(@RequestParam String producer, int volume, int sellerNum, String type, int cost, String name) {
        WashingMachine machine = new WashingMachine();
        machine.setCost(cost);
        machine.setTypeOf(type);
        machine.setName(name);
        machine.setSellerNum(sellerNum);
        machine.setVolume(volume);
        machine.setProducer(producer);
        service.create(machine);
        return "status: ok";
    }

    @PutMapping("/update")
    public String updateMachine(@RequestParam long id, String producer, int volume, int sellerNum, String type, int cost, String name) {
        WashingMachine machine = new WashingMachine();
        machine.setId(id);
        machine.setCost(cost);
        machine.setTypeOf(type);
        machine.setName(name);
        machine.setSellerNum(sellerNum);
        machine.setVolume(volume);
        machine.setProducer(producer);
        service.update(machine);
        return "status: ok";
    }

    @DeleteMapping("/delete")
    public String deleteMachine(@RequestParam long id) {
        service.delete(id);
        return "status: ok";
    }
}