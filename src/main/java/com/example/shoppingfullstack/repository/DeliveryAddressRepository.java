package com.example.shoppingfullstack.repository;

import com.example.shoppingfullstack.entity.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAdressRepository extends JpaRepository<DeliveryAddress, Long> {
}
