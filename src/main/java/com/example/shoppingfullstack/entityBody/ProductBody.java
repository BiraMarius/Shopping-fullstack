package com.example.shoppingfullstack.entityBody;

import com.example.shoppingfullstack.entity.Spec;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
public class ProductBody {

    private String categoryName;

    private String name;

    private String brand;

    private String description;

    private BigDecimal price;

    private String shortDescription;

    private String barcode;

    private Set<SpecBody> specifications;

}
