package com.example.shoppingfullstack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopDto {

    private Long id;

    private String name;

    private String email;

    private String  tel;

    private String workingHours;

    private Set<ProductDto> productList;
}
