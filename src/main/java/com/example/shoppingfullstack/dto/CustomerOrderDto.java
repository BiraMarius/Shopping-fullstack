package com.example.shoppingfullstack.dto;

import com.example.shoppingfullstack.util.DeliveryStatus;

public class ClientOrderDto {

    private Long id;

    private ShoppingCartDto cart;

    private DeliveryStatus status;

    private DeliveryAdressDto deliveryAdress;

    private ClientContactDto clientContactInfo;
}
