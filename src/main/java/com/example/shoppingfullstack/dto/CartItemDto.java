package com.example.shoppingfullstack.dto;

import java.math.BigDecimal;


public class CartItem {

    private Long id;

    private ShoppingCart shoppingCart;

    private Product product;

    private String name;

    private Long amount;

    private BigDecimal pricePerProduct;

    private BigDecimal totalPrice;
}
