package com.example.SpringPostgres.entities;

import com.example.SpringPostgres.entities.products.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @SequenceGenerator(name = "orders_seq", sequenceName = "orders_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "orders_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    private int count;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    Client client;

}
