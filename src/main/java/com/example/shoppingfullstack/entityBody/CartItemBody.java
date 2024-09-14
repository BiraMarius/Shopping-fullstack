package com.example.shoppingfullstack.entityBody;

import lombok.Data;

@Data
public class CartItemBody {
    private Long product_id;

    private Long cart_id;

    private Long amount;

}
