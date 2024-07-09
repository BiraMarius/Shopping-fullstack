package com.example.shoppingfullstack.dto;

import java.math.BigDecimal;
import java.util.Set;

public class ShoppingCart {

    private Long id;

    private Customer customer;

    private ClientOrder clientOrder;

    private Set<CartItem> items;

    private BigDecimal total;

    private String status;

}
