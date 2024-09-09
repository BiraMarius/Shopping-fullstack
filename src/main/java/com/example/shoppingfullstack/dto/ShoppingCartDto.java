package com.example.shoppingfullstack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartDto {

    private Long id;

    private CustomerDto customer;

    private CustomerOrderDto customerOrder;

    private Set<CartItemDto> items;

    private BigDecimal total;

    private String status;

}
