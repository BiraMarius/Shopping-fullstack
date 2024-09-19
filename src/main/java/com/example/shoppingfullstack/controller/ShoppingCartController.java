package com.example.shoppingfullstack.controller;

import com.example.shoppingfullstack.entity.CartItem;
import com.example.shoppingfullstack.entity.Customer;
import com.example.shoppingfullstack.entity.ShoppingCart;
import com.example.shoppingfullstack.entityBody.CartItemBody;
import com.example.shoppingfullstack.exception.ThisIsAGeneralException;
import com.example.shoppingfullstack.repository.CustomerRepository;
import com.example.shoppingfullstack.repository.ShoppingCartRepository;
import com.example.shoppingfullstack.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final CustomerRepository customerRepository;
    private final ShoppingCartRepository shoppingCartRepository;

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

    @PostMapping("/add-item-to-cart")
    public String addItemToCart(@RequestBody CartItemBody cartItemBody){
        shoppingCartService.addItemToCart(cartItemBody);
        return "CartItem added.";
    }

    @DeleteMapping("/delete-cartItem")
    public String deleteCartItem(@RequestParam Long cartItemId, @RequestParam Long customerId){
        return shoppingCartService.deleteCartItemFromCart(cartItemId, customerId);
    }

    @GetMapping("/get-items-list")
    public CartItem getItemsList(@RequestParam Long cartId) throws RuntimeException{
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(cartId);
        if(shoppingCart.isPresent()){
            for(CartItem item : shoppingCart.get().getItems()){
                return item;
            }
        }
        throw new ThisIsAGeneralException("EEEEEEEEEE");
    }

    @PostMapping("/decrease-cartItem-amount")
    public String decreaseAmount(@RequestParam Long cartItemId, @RequestParam Long customerId){
        return shoppingCartService.removeAmountOfCartItem(cartItemId, customerId);
    }




}
