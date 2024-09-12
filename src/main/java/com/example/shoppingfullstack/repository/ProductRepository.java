package com.example.shoppingfullstack.repository;

import com.example.shoppingfullstack.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByBarcode(String barcode);
}
