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
    @JoinColumn(referencedColumnName = "id")
    private Category category;

    @ManyToMany(mappedBy = "productList")
    private Set<Shop> shops;

    @OneToOne(mappedBy = "product")
    private CartItem cartItem;

    @ManyToMany(mappedBy= "productList")
    private Set<Spec> specs;

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
    private String barcode;

    @Column
    private LocalDate addedDate;

    public Product(Category category, Set<Spec> specs, String name, String brand, String description, BigDecimal price, String shortDescription, String barcode, LocalDate addedDate) {
        this.category = category;
        this.specs = specs;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.shortDescription = shortDescription;
        this.barcode = barcode;
        this.addedDate = addedDate;
    }
}
