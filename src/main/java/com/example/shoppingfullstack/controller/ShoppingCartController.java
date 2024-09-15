package com.example.shoppingfullstack.controller;

import com.example.shoppingfullstack.entity.Customer;
import com.example.shoppingfullstack.repository.CustomerRepository;
import com.example.shoppingfullstack.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final CustomerRepository customerRepository;

    @PostMapping("/add-cart")
    public String addCart(@RequestParam Long customerId){
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            shoppingCartService.addOrReturnCart(customer.get());
            return "Cart added.";
        } else {
            return "Customer not found";
        }
    }
}
