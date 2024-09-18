package com.example.shoppingfullstack.service;

import com.example.shoppingfullstack.entity.CartItem;
import com.example.shoppingfullstack.entity.Product;
import com.example.shoppingfullstack.entity.ShoppingCart;
import com.example.shoppingfullstack.entityBody.CartItemBody;
import com.example.shoppingfullstack.exception.ThisIsAGeneralException;
import com.example.shoppingfullstack.repository.CartItemRepository;
import com.example.shoppingfullstack.repository.ProductRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    private BigDecimal calculateTotalPerCartItem(Long amount, BigDecimal pricePerProduct){
        return pricePerProduct.multiply(BigDecimal.valueOf(amount));
    }

    public CartItem checkCartItemByProductAndCartInDatabase(CartItem cartItem, ShoppingCart shoppingCart){
        Optional<CartItem> cartItemOptional = cartItemRepository.findCartItemByShoppingCartAndProduct(shoppingCart,cartItem.getProduct());
        if(cartItemOptional.isPresent()){
            return cartItemOptional.get();
        }
        return null;
    }

    public CartItem saveItemToRepositoryAndUpdate(CartItem cartItem) throws RuntimeException{
        Optional<Product> productOptional = productRepository.findById(cartItem.getProduct().getId());
        if(productOptional.isPresent()){
            cartItem.setTotalPrice(calculateTotalPerCartItem(cartItem.getAmount(), productOptional.get().getPrice()));
            cartItemRepository.save(cartItem);
            return cartItem;
        }
        throw new ThisIsAGeneralException("Something went wrong. The product you are trying to add in your shopping cart doesn't exist in database. ERROR:102");
    }

    public CartItem createCartItemFromCartItemBody(CartItemBody cartItemBody, ShoppingCart shoppingCart) throws RuntimeException{
        Optional<Product> productOptional = productRepository.findById(cartItemBody.getProduct_id());
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            return CartItem.builder()
                    .name(product.getName())
                    .product(product)
                    .amount(cartItemBody.getAmount())
                    .pricePerProduct(product.getPrice())
                    .totalPrice(calculateTotalPerCartItem(cartItemBody.getAmount(), product.getPrice()))
                    .shoppingCart(shoppingCart)
                    .build();
        }
        throw new ThisIsAGeneralException("Something went wrong. The product you are trying to add in your shopping cart doesn't exist in database. ERROR:103");
    }

    public CartItem findCartItemById(Long cartItemId) throws RuntimeException{
        Optional<CartItem> cartItemOptional = cartItemRepository.findCartItemById(cartItemId);
        if(cartItemOptional.isPresent()){
            return cartItemOptional.get();
        }
        throw new ThisIsAGeneralException("CartItem not found. ERROR:104");
    }

    public void deleteCartItemFromRepository(CartItem cartItem){
        cartItemRepository.delete(cartItem);
    }


}
