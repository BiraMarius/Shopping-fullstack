package com.example.shoppingfullstack.controller;

import com.example.shoppingfullstack.entity.Customer;
import com.example.shoppingfullstack.entityBody.AddressOfCustomerBody;
import com.example.shoppingfullstack.entityBody.CustomerBody;
import com.example.shoppingfullstack.service.AddressOfCustomerService;
import com.example.shoppingfullstack.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final AddressOfCustomerService addressOfCustomerService;

    //This controller add a new customer in database only if the specific customerBody that has firstname, lastname, email and phone
    // that doesn't exist in database already.
    @PostMapping("/add-customer")
    public String addCustomer(@RequestBody CustomerBody customerBody){
        customerService.addCustomer(customerBody);
        return "Customer added.";
    }

    //This controller verify if a customer exists in database, it verify by a customerBody that has firstname, lastname, email and phone.
    @GetMapping("/verify-customer")
    public Customer verifyCustomer(@RequestBody CustomerBody customerBody){
        return customerService.checkIfCustomerExists(customerBody);
    }

    @PostMapping("/add-address-of-customer")
    public String addAddressOfCustomer(@RequestBody AddressOfCustomerBody addressOfCustomerBody){
        addressOfCustomerService.addAddressOfCustomer(addressOfCustomerBody);
        return "Address added in database.";
    }
}
