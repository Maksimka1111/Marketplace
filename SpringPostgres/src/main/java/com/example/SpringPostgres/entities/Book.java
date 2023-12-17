package com.example.SpringPostgres.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {
    @Id
    @SequenceGenerator(name = "books_seq", sequenceName = "books_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "books_seq", strategy = GenerationType.SEQUENCE)
    Long id;

    String author;
    int amount;
    Long sellerId;
    String typeOf;
    int cost;
    String name;

    public String toString(){
        return "id: " + id
                + "\nauthor: " + author
                + "\nseller number: " + sellerId
                + "\ntype: " + typeOf
                + "\ncost: " + cost
                + "\nname: " + name;
    }
}

