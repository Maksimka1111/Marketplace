package com.example.SpringPostgres.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @OneToMany(mappedBy = "client")
    private List<Order> basket;

    public String toString(){
        StringBuilder orders = new StringBuilder();
        for(Order order: basket){
            orders.append(order.toString()).append("\n");
        }
        if (basket.size() == 0)
            orders.append("no orders yet");
        return "id: " + id
                + "\nname: " + name
                + "\nmail: " + mail
                + "\nusername: " + username
                + "\npassword: " + password
                + "\norders: " + orders;
    }
}
