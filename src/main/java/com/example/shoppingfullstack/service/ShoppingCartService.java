package com.example.shoppingfullstack.service;

import com.example.shoppingfullstack.entity.Customer;
import com.example.shoppingfullstack.entity.ShoppingCart;
import com.example.shoppingfullstack.repository.ShoppingCartRepository;
import com.example.shoppingfullstack.util.CartStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart addOrReturnCart(Customer customer){
        for(ShoppingCart shoppingCart : customer.getCarts()){
            if(shoppingCart.getStatus().equals(CartStatus.ACTIVE)){
                return shoppingCart;
            }
        }
        ShoppingCart shoppingCart = new ShoppingCart(customer, new HashSet<>(), BigDecimal.valueOf(0), CartStatus.ACTIVE);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

}
