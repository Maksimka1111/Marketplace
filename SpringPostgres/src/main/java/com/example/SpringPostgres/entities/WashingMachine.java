package com.example.SpringPostgres.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mashines")
@Getter
@Setter
public class WashingMachine {
    @Id
    @SequenceGenerator(name = "machines_seq", sequenceName = "machines_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "machines_seq", strategy = GenerationType.SEQUENCE)
    Long id;

    String producer;
    int volume;
    Long sellerId;
    String typeOf;
    int amount;
    int cost;
    String name;

    public String toString(){
        return "id: " + id
                + "\nproducer: " + producer
                + "\nvolume: " + volume
                + "\nseller number: " + sellerId
                + "\ntype: " + typeOf
                + "\ncost: " + cost
                + "\nname: " + name;
    }
}
