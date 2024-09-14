package com.example.shoppingfullstack.service;

import com.example.shoppingfullstack.entity.CartItem;
import com.example.shoppingfullstack.entity.Product;
import com.example.shoppingfullstack.entity.ShoppingCart;
import com.example.shoppingfullstack.entityBody.CartItemBody;
import com.example.shoppingfullstack.exception.ThisIsAGeneralException;
import com.example.shoppingfullstack.repository.CartItemRepository;
import com.example.shoppingfullstack.repository.ProductRepository;
import com.example.shoppingfullstack.repository.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemService {

    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;

    public CartItem productToCartItem(CartItemBody cartItemBody) throws RuntimeException{
        Optional<Product> productOpt = productRepository.findById(cartItemBody.getProduct_id());
        Optional<ShoppingCart> shoppingCartOpt = shoppingCartRepository.findById(cartItemBody.getCart_id());
        if(productOpt.isPresent()){
            if(shoppingCartOpt.isPresent()){
                Product product = productOpt.get();
                ShoppingCart shoppingCart = shoppingCartOpt.get();
                CartItem cartItem = CartItem.builder()
                        .name(product.getName())
                        .product(product)
                        .amount(cartItemBody.getAmount())
                        .pricePerProduct(product.getPrice())
                        .totalPrice(calculateTotalPerCartItem(cartItemBody.getAmount(), product.getPrice()))
                        .shoppingCart(shoppingCart)
                        .build();
                Optional<CartItem> cartItemOpt = cartItemRepository.findCartItemByShoppingCartAndProductAndNameAndAmount(shoppingCart, product, product.getName(), cartItem.getAmount());
                if(cartItemOpt.isPresent()){
                    return cartItemOpt.get();
                }
                else {
                    cartItemRepository.save(cartItem);
                    return cartItem;
                }
            }
        }
        throw new ThisIsAGeneralException("There is a problem with tour product or shopping cart.");
    }

    public BigDecimal calculateTotalPerCartItem(Long amount, BigDecimal pricePerProduct){
        return pricePerProduct.multiply(BigDecimal.valueOf(amount));
    }
}
