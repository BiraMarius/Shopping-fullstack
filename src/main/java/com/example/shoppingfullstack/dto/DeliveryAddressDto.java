package com.example.shoppingfullstack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryAddressDto {

    private Long id;

    private String country;

    private String county;

    private String city;

    private String postalCode;

    private String street;

    private String number;

    private String building;

    private String additionalInfo;
}
