package com.example.shoppingfullstack.dto;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;


public class Customer {

    private Long id;

    private Set<ShoppingCart> carts;

    private List<AdressOfCustomer> addressesOfCustomer;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private LocalDate addedDate;
}
