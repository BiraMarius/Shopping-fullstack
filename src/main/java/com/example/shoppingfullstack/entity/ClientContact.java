package com.example.shoppingfullstack.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="Client_contact_info")
@Data
public class ClientContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phone;

    @Column
    private String email;
}
