package com.example.shoppingfullstack.entity;

import com.example.shoppingfullstack.util.CartStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name="Carts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    private CustomerOrder customerOrder;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> items;

    @Column
    private BigDecimal total;

    @Column
    private CartStatus status;

    public ShoppingCart(Customer customer, Set<CartItem> items, BigDecimal total, CartStatus status) {
        this.customer = customer;
        this.items = items;
        this.total = total;
        this.status = status;
    }
}
