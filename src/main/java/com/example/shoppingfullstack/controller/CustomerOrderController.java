package com.example.shoppingfullstack.controller;

import com.example.shoppingfullstack.entity.CustomerOrder;
import com.example.shoppingfullstack.service.CustomerOrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerOrderController {
    private final CustomerOrderService customerOrderService;

    @GetMapping("/get-orders")
    public String getCustomerOrders(){
        return customerOrderService.getAllOrders();
    }
}
