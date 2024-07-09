package com.example.shoppingfullstack.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="Customer_contact_info")
@Data
public class CustomerContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "customerContactInfo", cascade = CascadeType.ALL)
    private CustomerOrder customerOrder;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phone;

    @Column
    private String email;
}
