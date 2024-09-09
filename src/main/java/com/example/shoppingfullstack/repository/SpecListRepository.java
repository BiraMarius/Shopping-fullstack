package com.example.shoppingfullstack.repository;

import com.example.shoppingfullstack.entity.SpecsList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecListRepository extends JpaRepository<SpecsList, Long> {
}
