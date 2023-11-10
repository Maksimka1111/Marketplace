package com.example.SpringPostgres.entities;

import com.example.SpringPostgres.entities.products.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client {
    @Id
    @SequenceGenerator(name = "clients_seq", sequenceName = "clients_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "clients_seq", strategy = GenerationType.SEQUENCE)
    private Long id;


    private String name;
    private String mail;
    private String username;
    private String password;

    @OneToMany
    private List<Order> basket;

    public String toString(){
        return "id: " + id
                + "\nname: " + name
                + "\nmail: " + mail
                + "\nusername: " + username
                + "\npassword: " + password;
    }
}
