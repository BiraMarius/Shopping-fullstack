package com.example.shoppingfullstack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name="Spec")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Spec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name="Spec-products", joinColumns= @JoinColumn(name = "product_id"), inverseJoinColumns= @JoinColumn(name = "spec_id"))
    private Set<Product> productList;

    @Column
    private String name;

    @Column
    private String valueOfSpec;

    public Spec(String name, String valueOfSpec) {
        this.name = name;
        this.valueOfSpec = valueOfSpec;
    }
}
