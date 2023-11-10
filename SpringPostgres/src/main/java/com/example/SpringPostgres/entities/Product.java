package com.example.SpringPostgres.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
    @Id
    @SequenceGenerator(name = "product_seq", sequenceName = "product_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "product_seq", strategy = GenerationType.SEQUENCE)
    Long id;

    private String author;
    private String name;
    private int cost;
    private int volume;
    private int sellerNum;
    private String typeOf;

    public String toString(){
        if (typeOf.toLowerCase().equals("book")){
            return "id: " + id
                    + "\nauthor: " + author
                    + "\nseller number: " + sellerNum
                    + "\ntype: " + typeOf
                    + "\ncost: " + cost
                    + "\nname: " + name;
        }
        return "id: " + id
                + "\nproducer: " + author
                + "\nvolume: " + volume
                + "\nseller number: " + sellerNum
                + "\ntype: " + typeOf
                + "\ncost: " + cost
                + "\nname: " + name;
    }
    @OneToOne(mappedBy = "product")
    Order order;
}
