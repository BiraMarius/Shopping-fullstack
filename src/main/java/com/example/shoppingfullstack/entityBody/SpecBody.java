package com.example.shoppingfullstack.entityBody;

import com.example.shoppingfullstack.entity.SpecsList;
import lombok.Data;

@Data
public class SpecBody {

    private SpecsList specsList;

    private String name;

    private String valueOfSpec;
}
