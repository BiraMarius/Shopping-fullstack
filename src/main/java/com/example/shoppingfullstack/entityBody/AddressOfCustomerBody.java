package com.example.shoppingfullstack.entityBody;

import lombok.Data;

@Data
public class AdressOfCustomerBody {

    private String customerEmail;

    private String country;

    private String county;

    private String city;

    private String postalCode;

    private String street;

    private String number;

    private String building;

    private String additionalInfo;
}
