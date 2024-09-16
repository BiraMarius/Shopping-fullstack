package com.example.shoppingfullstack.service;

import com.example.shoppingfullstack.entity.CartItem;
import com.example.shoppingfullstack.entity.Customer;
import com.example.shoppingfullstack.entity.ShoppingCart;
import com.example.shoppingfullstack.entityBody.CartItemBody;
import com.example.shoppingfullstack.exception.ThisIsAGeneralException;
import com.example.shoppingfullstack.repository.CustomerRepository;
import com.example.shoppingfullstack.repository.ShoppingCartRepository;
import com.example.shoppingfullstack.util.CartStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final CustomerRepository customerRepository;
    private final CartItemService cartItemService;

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

    private BigDecimal updateCartTotalPrice(ShoppingCart shoppingCart){
        Set<CartItem> items = shoppingCart.getItems();
        BigDecimal totalOfCart=BigDecimal.valueOf(0);
        for(CartItem cartItem : items){
            totalOfCart = totalOfCart.add(cartItem.getTotalPrice());
        }
        return totalOfCart;
    }

    public CartItem addItemToCart(CartItemBody cartItembody){
        Customer customer = findCustomer(cartItembody.getCustomer_id());//Checking if the customer from cartItemBody exist.
        ShoppingCart shoppingCart = addOrReturnCart(customer);//Checking if the customer have any active cart to return, if not create a new one.
        CartItem cartItem = cartItemService.createCartItemFromCartItemBody(cartItembody,shoppingCart);//Create a cartItem using cartItemBody attributes.
        Set<CartItem> items = shoppingCart.getItems();//Getting the shoppingCart<CartItem> items List.

        //Checking if the product that needs to be added exist already in shoppingCart<CartItem> items List.
        for (CartItem item : items){
            if(item.getProduct().equals(cartItem.getProduct())){
                //YES --- The product desired exist in shoppingCart<CartItem> items List.
                //CartItem cartItemFromCart = item;  //Creating a new object to store the item.
                //items.remove(item);
                Long amount = item.getAmount() + cartItembody.getAmount();//update cartitem cu amount
                //cartItemFromCart.setAmount(amount);
                //cauta cartItem by  produsul in BD
                CartItem cartItem1 = cartItemService.checkCartItemByProductAndCartInDatabase(item, shoppingCart);
                if(cartItem1 != null){
                    //da
                    //take the obj
                    //save to items
                    //update cart total
                    //save cart
                    return saveAndReturnCartItem(cartItem1, amount, items, cartItem1, shoppingCart);
                } else {
                    //nu
                    //save the obj in BD
                    //save to items
                    //update cart total
                    //save cart
                    return saveAndReturnCartItem(item, amount, items, cartItem, shoppingCart);
                }
            }


        }
        //The product that the customer wants to add doesn't exist in shoppingCart<CartItem> items List. That means it's a new cartItem in shoppingCart.
        //Checking if cartItem exist in DB.
        CartItem cartItem1 = cartItemService.checkCartItemByProductAndCartInDatabase(cartItem, shoppingCart);//Searching the cartItem by product and shoppingCart in DB.
            if(cartItem1 != null){
                //YES --- cartItem exists in DB, with the desired product, but it doesn't exist in shoppingCart<CartItem> items List.
                cartItem1.setAmount(cartItem.getAmount());//Set amount with new amount.
                cartItemService.saveItemToRepositoryAndUpdate(cartItem1);//Because it exists in DB update amount and update entity in DB.
                items.add(cartItem1);//Add the object in shoppingCart<CartItem> items list.
                shoppingCart.setItems(items);//Set items to shoppingCart<CartItem> items list.
                shoppingCart.setTotal(updateCartTotalPrice(shoppingCart));//Update shoppingCart total price.
                shoppingCartRepository.save(shoppingCart);//Update shoppingCart.
                return cartItem1;
            } else {
                //NO --- cartItem doesn't exist in DB, that means the product that the customer is trying to add doesn't exist in DB.
                //save to database cartitem
                //save to items
                //update cart total price
                //save cart

                cartItemService.saveItemToRepositoryAndUpdate(cartItem);
                items.add(cartItem);
                shoppingCart.setItems(items);
                shoppingCart.setTotal(updateCartTotalPrice(shoppingCart));
                shoppingCartRepository.save(shoppingCart);
                return cartItem;
            }
    }

    private CartItem saveAndReturnCartItem(CartItem item, Long amount, Set<CartItem> items, CartItem cartItemToBeAddedInItems, ShoppingCart shoppingCart) {
        item.setAmount(amount);
        items.remove(item);
        items.add(cartItemToBeAddedInItems);
        cartItemService.saveItemToRepositoryAndUpdate(item);
        shoppingCart.setItems(items);
        shoppingCart.setTotal(updateCartTotalPrice(shoppingCart));
        shoppingCartRepository.save(shoppingCart);
        return item;
    }

    //                         MOVE TO CUSTOMER SERVICE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public Customer findCustomer(Long customerId) throws RuntimeException{
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent()){
            return customerOptional.get();
        } else {
            throw new ThisIsAGeneralException("Customer not found. Error:101");
        }
    }

//    public void addItemToCartModern(CartItemBody cartItembody){
//        Customer customer = findCustomer(cartItembody.getCustomer_id());//Checking if the customer from cartItemBody exist.
//        ShoppingCart shoppingCart = addOrReturnCart(customer);//Checking if the customer have any active cart to return, if not create a new one.
//        CartItem cartItem = cartItemService.createCartItemFromCartItemBody(cartItembody,shoppingCart);//Create a cartItem using cartItemBody attributes.
//        Set<CartItem> items = shoppingCart.getItems();//Getting the shoppingCart<CartItem> items List.
//
//        //Checking if the product that needs to be added exist already in shoppingCart<CartItem> items List.
//        for (CartItem item : items){
//            if(item.getProduct().equals(cartItem.getProduct())){
//                //YES --- The product desired exist in shoppingCart<CartItem> items List.
//                //CartItem cartItemFromCart = item;  //Creating a new object to store the item.
//                items.remove(item);
//                Long amount = item.getAmount() + cartItembody.getAmount();//update cartitem cu amount
//                item.setAmount(amount);
//                //cauta cartItem by  produsul in BD
//                CartItem cartItem1 = cartItemService.checkCartItemByProductAndCartInDatabase(item, shoppingCart);
//                if(cartItem1 != null){
//                    //da
//                    //take the obj
//                    //save to items
//                    //update cart total
//                    //save cart
//                    cartItem1.setAmount(amount);
//                    items.add(cartItem1);
//                    cartItemService.saveItemToRepositoryAndUpdate(cartItem1);
//                    shoppingCart.setItems(items);
//                    updateCartTotalPrice(shoppingCart);
//                    shoppingCartRepository.save(shoppingCart);
//                } else {
//                    //nu
//                    //save the obj in BD
//                    //save to items
//                    //update cart total
//                    //save cart
//                    cartItemService.saveItemToRepositoryAndUpdate(item);
//                    items.add(cartItem);
//                    shoppingCart.setItems(items);
//                    updateCartTotalPrice(shoppingCart);
//                    shoppingCartRepository.save(shoppingCart);
//
//                }
//            }
//
//
//        }
//        //The product that the customer wants to add doesn't exist in shoppingCart<CartItem> items List. That means it's a new cartItem in shoppingCart.
//        //Checking if cartItem exist in DB.
//        CartItem cartItem1 = cartItemService.checkCartItemByProductAndCartInDatabase(cartItem, shoppingCart);//Searching the cartItem by product and shoppingCart in DB.
//        if(cartItem1 != null){
//            //YES --- cartItem exists in DB, with the desired product, but it doesn't exist in shoppingCart<CartItem> items List.
//            cartItem1.setAmount(cartItem.getAmount());//Set amount with new amount.
//            cartItemService.saveItemToRepositoryAndUpdate(cartItem1);//Because it exists in DB update amount and update entity in DB.
//            items.add(cartItem1);//Add the object in shoppingCart<CartItem> items list.
//            shoppingCart.setItems(items);//Set items to shoppingCart<CartItem> items list.
//            updateCartTotalPrice(shoppingCart);//Update shoppingCart total price.
//            shoppingCartRepository.save(shoppingCart);//Update shoppingCart.
//        } else {
//            //NO --- cartItem doesn't exist in DB, that means the product that the customer is trying to add doesn't exist in DB.
//            //save to database cartitem
//            //save to items
//            //update cart total price
//            //save cart
//
//            cartItemService.saveItemToRepositoryAndUpdate(cartItem);
//            items.add(cartItem);
//            shoppingCart.setItems(items);
//            updateCartTotalPrice(shoppingCart);
//            shoppingCartRepository.save(shoppingCart);
//        }
//    }

}
