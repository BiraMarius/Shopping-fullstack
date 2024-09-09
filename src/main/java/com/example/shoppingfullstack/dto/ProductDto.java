package com.example.shoppingfullstack.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private Long id;

    private CategoryDto category;

    private Set<ShopDto> shops;

    private CartItemDto cartItem;

    private String name;

    private String brand;

    private String description;

    private BigDecimal price;

    private String shortDescription;

    private Long stock;

    private LocalDate addedDate;
}
