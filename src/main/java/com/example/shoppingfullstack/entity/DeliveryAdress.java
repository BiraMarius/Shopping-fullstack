package com.example.shoppingfullstack.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "Delivery_adresses")
@Data
public class DeliveryAdress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String country;

    @Column
    private String county;

    @Column
    private String city;

    @Column
    private String postalCode;

    @Column
    private String street;

    @Column
    private String number;

    @Column
    private String building;

    @Column
    private String additionalInfo;
}
