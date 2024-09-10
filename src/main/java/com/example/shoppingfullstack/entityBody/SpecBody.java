package com.example.shoppingfullstack.entityBody;

import com.example.shoppingfullstack.entity.SpecsList;
import lombok.Data;

@Data
public class SpecBody {

    private String categoryName;

    private String name;

    private String valueOfSpec;
}
