package com.example.shoppingfullstack.repository;

import com.example.shoppingfullstack.entity.AddressOfCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressOfCustomerRepository extends JpaRepository<AddressOfCustomer, Long> {
}
