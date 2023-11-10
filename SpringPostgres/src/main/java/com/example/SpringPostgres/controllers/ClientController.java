package com.example.SpringPostgres.controllers;

import com.example.SpringPostgres.entities.Client;
import com.example.SpringPostgres.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService service;

    @GetMapping("/read")
    public String readClient(@RequestParam long id){
        return service.read(id).toString();
    }
    @PostMapping("/create")
    public String createClient(@RequestParam String username, String mail, String name, String password){
        Client client = new Client();
        client.setMail(mail);
        client.setName(name);
        client.setUsername(username);
        client.setPassword(password);
        service.create(client);
        return "status: ok";
    }
    @PutMapping("/update")
    public String updateClient(@RequestParam long id, String username, String mail, String name, String password){
        Client client = new Client();
        client.setId(id);
        client.setMail(mail);
        client.setName(name);
        client.setUsername(username);
        client.setPassword(password);
        service.update(client);
        return "status: ok";
    }
    @DeleteMapping("/delete")
    public String deleteClient(@RequestParam long id){
        service.delete(id);
        return "status: ok";
    }
}
