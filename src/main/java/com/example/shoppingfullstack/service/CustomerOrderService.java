package com.example.shoppingfullstack.service;

import com.example.shoppingfullstack.dto.CustomerOrderDto;
import com.example.shoppingfullstack.entity.CustomerOrder;
import com.example.shoppingfullstack.repository.CustomerOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerOrderService {
    private final CustomerOrderRepository customerOrderRepository;

    public String getAllOrders(){
        List<CustomerOrder> ordersFromCustomers = customerOrderRepository.findAll();
        for(CustomerOrder customerOrder : ordersFromCustomers){
            String s ="NO: "+ customerOrder.getId()+"    "+customerOrder.getStatus();

            return s;
        }
        //return ordersFromCustomers;
        return "Failed";
    }



}
