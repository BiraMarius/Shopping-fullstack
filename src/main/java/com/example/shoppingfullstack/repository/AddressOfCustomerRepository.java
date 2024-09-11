package com.example.shoppingfullstack.repository;

import com.example.shoppingfullstack.entity.AdressOfCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressOfCustomerRepository extends JpaRepository<AdressOfCustomer, Long> {
}
