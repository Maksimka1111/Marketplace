package com.example.SpringPostgres.entities.products;

import com.example.SpringPostgres.entities.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @SequenceGenerator(name = "products_seq", sequenceName = "products_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "products_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String author;
    private int volume;
    private int sellerNum;
    private String typeOf;
    private String name;
    private int cost;

    public String toString(){
        if (volume != 0)
            return "id: " + id
                + "\nproducer: " + author
                + "\nvolume: " + volume
                + "\nseller number: " + sellerNum
                + "\ntype: " + typeOf
                + "\ncost: " + cost
                + "\nname: " + name;
        return "id: " + id
                    + "\nauthor: " + author
                    + "\nseller number: " + sellerNum
                    + "\ntype: " + typeOf
                    + "\ncost: " + cost
                    + "\nname: " + name;
    }

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "product")
    Order order;

}
