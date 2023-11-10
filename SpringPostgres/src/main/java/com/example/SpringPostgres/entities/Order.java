package com.example.SpringPostgres.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order")
@Getter
@Setter
public class Order {
    @Id
    @SequenceGenerator(name = "order_seq", sequenceName = "order_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "order_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    private int amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="client_id")
    Client client;

    @Override
    public String toString() {
        return "order id: " + id + "\nProduct: \n" + product.toString()  + "\n" + "amount: " + amount;
    }
}
