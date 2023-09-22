package com.jasper.pigrakker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="orderitems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Product product;
    @OneToOne
    private Order order;
    @NotEmpty(message = "hoeveelheid mag niet leeg zijn.")
    private int quantity;

}
