package com.example.shoppingfullstack.entityBody;

import lombok.Data;

import java.time.LocalDate;


@Data
public class CustomerBody {

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private LocalDate addedDate;
}
