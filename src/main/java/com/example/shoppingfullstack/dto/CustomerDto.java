package com.example.shoppingfullstack.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private Long id;

    private Set<ShoppingCartDto> carts;

    private List<AdressOfCustomerDto> addressesOfCustomer;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private LocalDate addedDate;
}
