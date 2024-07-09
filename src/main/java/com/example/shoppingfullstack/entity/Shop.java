package com.example.shoppingfullstack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name="Shop")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    public Shop(String name, String email, String tel, String workingHours) {
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.workingHours = workingHours;
    }
}
