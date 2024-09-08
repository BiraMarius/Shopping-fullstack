package com.example.shoppingfullstack.entity;

import jakarta.persistence.*;

import lombok.Data;


@Entity
@Table(name="Orders")
@Data
public class ClientOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private ShoppingCart cart;

    @Column
    private Enum status;

    @OneToOne
    @JoinColumn(name = "deliveryAdress_id")
    private DeliveryAdress deliveryAdress;

    @OneToOne
    private ClientContact clientContactInfo;
}
