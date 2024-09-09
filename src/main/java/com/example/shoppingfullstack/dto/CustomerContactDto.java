package com.example.shoppingfullstack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerContactDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;
}
