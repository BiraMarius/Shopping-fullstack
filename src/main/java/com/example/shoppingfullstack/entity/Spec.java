package com.example.shoppingfullstack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name="specsList_id")
    private SpecsList specsList;

    @Column
    private String name;

    @Column
    private String valueOfSpec;

    public Spec(SpecsList specsList, String name, String valueOfSpec) {
        this.specsList = specsList;
        this.name = name;
        this.valueOfSpec = valueOfSpec;
    }
}
