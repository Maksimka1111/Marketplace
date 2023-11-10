package com.example.SpringPostgres.entities.products;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book extends Product {
    @Id
    @SequenceGenerator(name = "books_seq", sequenceName = "books_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "books_seq", strategy = GenerationType.SEQUENCE)
    Long id;

    String author;
    int sellerNum;
    String typeOf;
    int cost;
    String name;

    public String toString(){
        return "id: " + id
                + "\nauthor: " + author
                + "\nseller number: " + sellerNum
                + "\ntype: " + typeOf
                + "\ncost: " + cost
                + "\nname: " + name;
    }
}

