package com.example.shoppingfullstack.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Spec")
@Data
public class Spec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="specsList_id")
    private SpecsList specsList;

    @Column
    private String name;

    @Column
    private String valueOfSpec;
}
