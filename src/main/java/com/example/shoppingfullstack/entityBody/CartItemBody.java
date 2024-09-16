package com.example.shoppingfullstack.entityBody;

import lombok.Data;

@Data
public class CartItemBody {
    private Long product_id;

    private Long customer_id;

    private Long amount;

}
