package com.example.SpringPostgres.entities.products;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mashines")
@Getter
@Setter
public class WashingMachine extends Product {
    @Id
    @SequenceGenerator(name = "machines_seq", sequenceName = "machines_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "machines_seq", strategy = GenerationType.SEQUENCE)
    Long id;

    String producer;
    int volume;
    int sellerNum;
    String typeOf;
    int cost;
    String name;

    public String toString(){
        return "id: " + id
                + "\nproducer: " + producer
                + "\nvolume: " + volume
                + "\nseller number: " + sellerNum
                + "\ntype: " + typeOf
                + "\ncost: " + cost
                + "\nname: " + name;
    }
}
