package com.example.shoppingfullstack.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name="Items")
@Data

public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="shoppingCart_id")
    private ShoppingCart shoppingCart;

    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column
    private String name;

    @Column
    private Long amount;

    @Column
    private BigDecimal pricePerProduct;

    @Column
    private BigDecimal totalPrice;
}
