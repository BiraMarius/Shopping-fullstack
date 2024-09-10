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
public class SpecsListDto {

    private Long id;

    private Set<SpecDto> specs;

    private CategoryDto category;
}
