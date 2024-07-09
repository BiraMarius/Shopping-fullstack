package com.example.shoppingfullstack.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class Product {

    private Long id;

    private Category category;

    private Set<Shop> shops;

    private CartItem cartItem;

    private String name;

    private String brand;

    private String description;

    private BigDecimal price;

    private String shortDescription;

    private Long stock;

    private LocalDate addedDate;
}
