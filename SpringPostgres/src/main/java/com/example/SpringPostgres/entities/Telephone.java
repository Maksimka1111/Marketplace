package com.example.SpringPostgres.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "telephones")
@Getter
@Setter
public class Telephone{
    @Id
    @SequenceGenerator(name = "telephones_seq", sequenceName = "telephones_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "telephones_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String producer;
    int amount;
    private int volume;
    private Long sellerId;
    private String typeOf;
    private String name;
    private int cost;

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
