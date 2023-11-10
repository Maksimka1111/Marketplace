package com.example.SpringPostgres.entities.products;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "telephones")
@Getter
@Setter
public class Telephone extends Product{
    @Id
    @SequenceGenerator(name = "telephones_seq", sequenceName = "telephones_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "telephones_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String producer;
    private int volume;
    private int sellerNum;
    private String typeOf;
    private String name;
    private int cost;

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
