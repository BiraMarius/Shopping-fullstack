package com.example.shoppingfullstack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressOfCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

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

    public AddressOfCustomer(Customer customer, String country, String county, String city, String postalCode, String street, String number, String building, String additionalInfo) {
        this.customer = customer;
        this.country = country;
        this.county = county;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.number = number;
        this.building = building;
        this.additionalInfo = additionalInfo;
    }
}
