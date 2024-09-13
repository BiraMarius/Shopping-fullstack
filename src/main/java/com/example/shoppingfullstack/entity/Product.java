package com.example.shoppingfullstack.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    //Join table "spec-products" not saving any keys
//    @ManyToMany(mappedBy= "productList")
//    private Set<Spec> specs;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "Spec-products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "spec_id")
    )
    private Set<Spec> specs = new HashSet<>();

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
