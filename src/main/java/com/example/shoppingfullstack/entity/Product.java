package com.example.shoppingfullstack.entity;

import jakarta.persistence.*;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="Products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "productList")
    private Set<Shop> shops;

    @OneToOne(mappedBy = "product")
    private CartItem cartItem;

    @Column
    private String name;

    @Column
    private String brand;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    @Column
    private String shortDescription;

    @Column
    private Long stock;

    @Column
    private LocalDate addedDate;
}
