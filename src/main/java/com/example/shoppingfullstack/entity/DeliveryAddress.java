package com.example.shoppingfullstack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Delivery_addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "deliveryAddress", cascade = CascadeType.ALL)
    private CustomerOrder customerOrder;

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
