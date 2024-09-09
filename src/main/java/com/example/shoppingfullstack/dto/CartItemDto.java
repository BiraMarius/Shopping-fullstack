package com.example.shoppingfullstack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDto {

    private Long id;

    private ShoppingCartDto shoppingCart;

    private ProductDto product;

    private String name;

    private Long amount;

    private BigDecimal pricePerProduct;

    private BigDecimal totalPrice;
}
