package com.example.shoppingfullstack.dto;

import com.example.shoppingfullstack.util.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerOrderDto {

    private Long id;

    private ShoppingCartDto cart;

    private DeliveryStatus status;

    private DeliveryAdressDto deliveryAdress;

    private CustomerContactDto customerContactInfo;
}
