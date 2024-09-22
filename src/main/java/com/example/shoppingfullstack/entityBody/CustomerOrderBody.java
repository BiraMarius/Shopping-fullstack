package com.example.shoppingfullstack.entityBody;

import com.example.shoppingfullstack.entity.AddressOfCustomer;
import com.example.shoppingfullstack.entity.CustomerContact;
import com.example.shoppingfullstack.entity.DeliveryAddress;
import com.example.shoppingfullstack.entity.ShoppingCart;
import lombok.Data;

@Data
public class CustomerOrderBody {

    private Long cartId;

    private AddressOfCustomer addressOfCustomer;

    private CustomerContact customerContactInfo;
}
