package com.example.shoppingfullstack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="Items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    public CartItem(ShoppingCart shoppingCart, Product product, String name, Long amount, BigDecimal pricePerProduct, BigDecimal totalPrice) {
        this.shoppingCart = shoppingCart;
        this.product = product;
        this.name = name;
        this.amount = amount;
        this.pricePerProduct = pricePerProduct;
        this.totalPrice = totalPrice;
    }
}
