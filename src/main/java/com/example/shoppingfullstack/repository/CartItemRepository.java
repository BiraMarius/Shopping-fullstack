package com.example.shoppingfullstack.repository;

import com.example.shoppingfullstack.entity.CartItem;
import com.example.shoppingfullstack.entity.Customer;
import com.example.shoppingfullstack.entity.Product;
import com.example.shoppingfullstack.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
//    @Query(value = "SELECT * FROM Customers c WHERE c.FIRST_NAME = :FIRST_NAME " +
//            "AND c.LAST_NAME = :LAST_NAME " +
//            "AND c.EMAIL = :EMAIL " +
//            "AND c.PHONE = :PHONE", nativeQuery = true)
//    Customer findCustomerByFirstNameAndLastNameAndEmailAndPhone(@Param("FIRST_NAME") String FIRST_NAME,
//                                                                @Param("LAST_NAME") String LAST_NAME,
//                                                                @Param("EMAIL") String EMAIL,
//                                                                @Param("PHONE") String PHONE);

    Optional<CartItem> findCartItemByShoppingCartAndProductAndNameAndAmount(ShoppingCart shoppingCart, Product product, String name, Long amount);

    Optional<CartItem> findCartItemByShoppingCartAndProduct(ShoppingCart shoppingCart, Product product);

    Optional<CartItem> findCartItemById(Long cartItemId);

}
