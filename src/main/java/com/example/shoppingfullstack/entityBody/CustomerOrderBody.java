package com.example.shoppingfullstack.entityBody;

import com.example.shoppingfullstack.entity.CustomerContact;
import com.example.shoppingfullstack.entity.DeliveryAddress;
import com.example.shoppingfullstack.entity.ShoppingCart;
import lombok.Data;

@Data
public class CustomerOrderBody {

    private Long cartId;

    private DeliveryAddress deliveryAddress;

    private CustomerContact customerContactInfo;
}
