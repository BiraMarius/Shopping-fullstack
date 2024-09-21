package com.example.shoppingfullstack.service;

import com.example.shoppingfullstack.entity.CartItem;
import com.example.shoppingfullstack.entity.Customer;
import com.example.shoppingfullstack.entity.CustomerOrder;
import com.example.shoppingfullstack.entity.ShoppingCart;
import com.example.shoppingfullstack.entityBody.CartItemBody;
import com.example.shoppingfullstack.entityBody.CustomerOrderBody;
import com.example.shoppingfullstack.exception.ThisIsAGeneralException;
import com.example.shoppingfullstack.repository.CustomerOrderRepository;
import com.example.shoppingfullstack.repository.ShoppingCartRepository;
import com.example.shoppingfullstack.util.CartStatus;
import com.example.shoppingfullstack.util.DeliveryStatus;
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
    private final CustomerService customerService;
    private final CartItemService cartItemService;
    private final CustomerOrderRepository customerOrderRepository;

    public ShoppingCart addOrReturnCart(Customer customer){
        ShoppingCart shoppingCart1 = findActiveShoppingCart(customer);
        if (shoppingCart1 != null) return shoppingCart1;
        ShoppingCart shoppingCart = new ShoppingCart(customer, new HashSet<>(), BigDecimal.valueOf(0), CartStatus.ACTIVE);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    private static ShoppingCart findActiveShoppingCart(Customer customer) {
        for(ShoppingCart shoppingCart : customer.getCarts()){
            if(shoppingCart.getStatus().equals(CartStatus.ACTIVE)){
                return shoppingCart;
            }
        }
        return null;
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
        Customer customer = customerService.findCustomer(cartItembody.getCustomer_id());//Checking if the customer from cartItemBody exist.
        ShoppingCart shoppingCart = addOrReturnCart(customer);//Checking if the customer have any active cart to return, if not create a new one.
        CartItem cartItem = cartItemService.createCartItemFromCartItemBody(cartItembody,shoppingCart);//Create a cartItem using cartItemBody attributes.
        Set<CartItem> items = shoppingCart.getItems();//Getting the shoppingCart<CartItem> items List.

        //Checking if the product that needs to be added exist already in shoppingCart<CartItem> items List.
        for (CartItem item : items){
            if(item.getProduct().equals(cartItem.getProduct())){
                //YES --- The product desired exist in shoppingCart<CartItem> items List.
                Long amount = item.getAmount() + cartItembody.getAmount();//update cartitem cu amount
                //Checking if cartItem exist in DB.
                CartItem cartItemFromDb = cartItemService.checkCartItemByProductAndCartInDatabase(item, shoppingCart);
                if(cartItemFromDb != null){
                    //YES --- cartItem exists in DB, with the desired product and exist in shoppingCart<CartItem> items List.
                    cartItemFromDb.setAmount(amount);//Set amount with new amount.
                    items.remove(cartItemFromDb);//Remove the item from shoppingCart<CartItem> items List.
                    return saveAllAddCartItem(cartItemFromDb, items, shoppingCart);
                } else {
                    //NO --- cartItem doesn't exist in DB, that means the product that the customer is trying to add doesn't exist in DB.
                    item.setAmount(amount);//Set amount with new amount.
                    items.remove(item);//Remove the item from shoppingCart<CartItem> items List.
                    return saveAllAddCartItem(item, items, shoppingCart);
                }
            }
        }
        //The product that the customer wants to add doesn't exist in shoppingCart<CartItem> items List. That means it's a new cartItem in shoppingCart.Checking if cartItem exist in DB.
        CartItem cartItemFromDb = cartItemService.checkCartItemByProductAndCartInDatabase(cartItem, shoppingCart);//Searching the cartItem by product and shoppingCart in DB.
            if(cartItemFromDb != null){
                //YES --- cartItem exists in DB, with the desired product, but it doesn't exist in shoppingCart<CartItem> items List.
                cartItemFromDb.setAmount(cartItem.getAmount());//Set amount with new amount.
                return saveAllAddCartItem(cartItemFromDb, items, shoppingCart);
            } else {
                //NO --- cartItem doesn't exist in DB, that means the product that the customer is trying to add doesn't exist in DB.
                return saveAllAddCartItem(cartItem, items, shoppingCart);
            }
    }

    private CartItem saveAllAddCartItem(CartItem itemToBeAddAndSaved, Set<CartItem> items, ShoppingCart shoppingCart) {
        cartItemService.saveItemToRepositoryAndUpdate(itemToBeAddAndSaved);//If it exists in DB update amount and update entity in DB.
        items.add(itemToBeAddAndSaved);//Add the object in shoppingCart<CartItem> items list.
        shoppingCart.setItems(items);//Set items to shoppingCart<CartItem> items list.
        shoppingCart.setTotal(updateCartTotalPrice(shoppingCart));//Update shoppingCart total price.
        shoppingCartRepository.save(shoppingCart);//Update shoppingCart.
        return itemToBeAddAndSaved;
    }

    public String deleteCartItemFromCart(Long cartItemId, Long customerId) throws RuntimeException{
        Customer customer = customerService.findCustomer(customerId);
        ShoppingCart shoppingCart = findActiveShoppingCart(customer);
        if(shoppingCart!=null){
            for(CartItem item : shoppingCart.getItems()){
                if (item.getId().equals(cartItemId)) {
                    cartItemService.deleteCartItemFromRepository(item);
                    shoppingCart.getItems().remove(item);
                    shoppingCart.setTotal(updateCartTotalPrice(shoppingCart));//Update shoppingCart total price.
                    shoppingCartRepository.save(shoppingCart);//Update shoppingCart.
                    return "Item removed.";
                }
            }
        }
        throw new ThisIsAGeneralException("Something went wrong. ERROR:105");
    }

    public String decreaseAmountOfCartItem(Long cartItemId, Long customerId) throws RuntimeException{
        Customer customer = customerService.findCustomer(customerId);
        ShoppingCart shoppingCart = findActiveShoppingCart(customer);
        if(shoppingCart!=null){
            for(CartItem item : shoppingCart.getItems()){
                if (item.getId().equals(cartItemId)) {
                    Long amount = item.getAmount();
                    item.setAmount(--amount);
                    if(amount>0){
                        changeOldItemWithNew(item, shoppingCart);
                        return "Item amount decreased.";
                    } else {
                        deleteCartItemFromCart(item.getId(), customerId);
                    }


                }
            }
        }
        throw new ThisIsAGeneralException("Something went wrong. ERROR:106");
    }

    private void changeOldItemWithNew(CartItem item, ShoppingCart shoppingCart) {
        shoppingCart.getItems().remove(item);
        cartItemService.updateRepository(item);
        shoppingCart.getItems().add(item);
        shoppingCart.setTotal(updateCartTotalPrice(shoppingCart));//Update shoppingCart total price.
        shoppingCartRepository.save(shoppingCart);//Update shoppingCart.
    }

    public String increaseAmountOfCartItem(Long cartItemId, Long customerId) throws RuntimeException{
        Customer customer = customerService.findCustomer(customerId);
        ShoppingCart shoppingCart = findActiveShoppingCart(customer);
        if(shoppingCart!=null){
            for(CartItem item : shoppingCart.getItems()){
                if(item.getId().equals(cartItemId)){
                    Long amount = item.getAmount();
                    item.setAmount(++amount);
                    changeOldItemWithNew(item, shoppingCart);
                    return "Item amount increased.";
                }
            }
        }
        throw new ThisIsAGeneralException("Something went wrong. ERROR:107");
    }

    public String orderThisCart(CustomerOrderBody customerOrderBody) throws RuntimeException{
        Optional<ShoppingCart> shoppingCartOpt = shoppingCartRepository.findById(customerOrderBody.getCartId());
        if(shoppingCartOpt.isPresent()){
            ShoppingCart shoppingCart = shoppingCartOpt.get();
            CustomerOrder customerOrder = new CustomerOrder(shoppingCart, DeliveryStatus.PREPARING_ORDER, customerOrderBody.getDeliveryAddress(), customerOrderBody.getCustomerContactInfo());
            customerOrderRepository.save(customerOrder);
            shoppingCart.setStatus(CartStatus.ORDERED);
            shoppingCartRepository.save(shoppingCart);
        }
        throw new ThisIsAGeneralException("Something went wrong, shopping cart not found. ERROR:108");
    }

}
