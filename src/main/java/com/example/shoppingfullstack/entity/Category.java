package com.example.shoppingfullstack.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "Category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products;

    @Column
    private String name;

    @OneToOne(mappedBy = "category", cascade = CascadeType.ALL)
    private SpecsList specs;
}
