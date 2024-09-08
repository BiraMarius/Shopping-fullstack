package com.example.shoppingfullstack.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name="SpecsList")
@Data
public class SpecsList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "specsList", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Spec> specs;

    @OneToOne
    @JoinColumn(name="category_id")
    private Category category;
}
