package com.example.SpringPostgres.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client {
    @Id
    @SequenceGenerator(name = "clients_seq", sequenceName = "clients_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "clients_seq", strategy = GenerationType.SEQUENCE)
    private Long id;


    private String username;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Order> basket;

    public void addOrder(Order order){
        this.basket.add(order);
    }
    public void delOrder(Order order){
        this.basket.remove(order);
    }

    public String toString(){
        StringBuilder orders = new StringBuilder();
        for(Order order: basket){
            orders.append(order.toString()).append("\n");
        }
        if (basket.size() == 0)
            orders.append("no orders yet");
        return "id: " + id
                + "\nusername: " + username
                + "\norders: " + orders;
    }
}
