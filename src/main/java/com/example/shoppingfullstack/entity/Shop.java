package com.example.shoppingfullstack.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name="Shop")
@Data
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String  tel;

    @Column
    private String workingHours;

    @ManyToMany
    @JoinTable(name="shop-products", joinColumns= @JoinColumn(name = "product_id"), inverseJoinColumns= @JoinColumn(name = "shop_id"))
    private Set<Product> productList;
}
