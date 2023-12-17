package com.example.SpringPostgres.controllers;

import com.example.SpringPostgres.entities.Client;
import com.example.SpringPostgres.services.ClientService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService service;

    @GetMapping("/becameSeller")
    @PreAuthorize("hasAnyRole('USER', 'ADMINISTRATOR')")
    public String wannaBeSeller(){
        return "redirect:" + "http://localhost:9001/user/becameSeller";
    }

    @GetMapping("/getUser")
    public @ResponseBody String getUser(){
        return service.getCurrentUser().getUsername();
    }
    @GetMapping("/read")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    public @ResponseBody String readClient(@RequestParam long id){
        return service.read(id).toString();
    }
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    public @ResponseBody String createClient(@RequestParam String username, String mail, String name, String password){
        Client client = new Client();
        client.setUsername(username);
        service.create(client);
        return "status: ok";
    }
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    public @ResponseBody String updateClient(@RequestParam long id, String username, String mail, String name, String password){
        Client client = new Client();
        client.setId(id);
        client.setUsername(username);
        service.update(client);
        return "status: ok";
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    public @ResponseBody String deleteClient(@RequestParam long id){
        service.delete(id);
        return "status: ok";
    }
}
