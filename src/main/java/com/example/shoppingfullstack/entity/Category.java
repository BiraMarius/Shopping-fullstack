package com.example.shoppingfullstack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "Category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    public Category(String name, SpecsList specs) {
        this.name = name;
        this.specs = specs;
    }
}
