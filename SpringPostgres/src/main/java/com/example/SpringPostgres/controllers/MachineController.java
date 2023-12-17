package com.example.SpringPostgres.controllers;

import com.example.SpringPostgres.entities.WashingMachine;
import com.example.SpringPostgres.services.ClientService;
import com.example.SpringPostgres.services.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/machine")
public class MachineController {
    @Autowired
    MachineService service;
    @Autowired
    ClientService clientService;

    @GetMapping("/readAll")
    @PreAuthorize("hasAnyRole('USER', 'SELLER', 'ADMIN')")
    public String readAllBook(){
        return service.readAll();
    }
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @GetMapping("/read")
    public String readMachine(@RequestParam long id) {
        return service.read(id).toString();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    public String createMachine(@RequestParam String producer, int volume, String type, int cost, String name, int amount) {
        WashingMachine machine = new WashingMachine();
        machine.setCost(cost);
        machine.setTypeOf(type);
        machine.setName(name);
        machine.setSellerId(clientService.getCurrentUser().getId());
        machine.setVolume(volume);
        machine.setProducer(producer);
        machine.setAmount(amount);
        service.create(machine);
        return "status: ok";
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    public String updateMachine(@RequestParam String producer, int volume, int sellerNum, String type, int cost, String name, int amount) {
        WashingMachine machine = new WashingMachine();
        machine.setCost(cost);
        machine.setTypeOf(type);
        machine.setName(name);
        machine.setVolume(volume);
        machine.setProducer(producer);
        machine.setAmount(amount);
        service.update(machine);
        return "status: ok";
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    public String deleteMachine(@RequestParam long id) {
        service.delete(id);
        return "status: ok";
    }
}