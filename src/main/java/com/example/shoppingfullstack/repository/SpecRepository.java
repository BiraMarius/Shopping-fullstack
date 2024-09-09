package com.example.shoppingfullstack.repository;

import com.example.shoppingfullstack.entity.Spec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecRepository extends JpaRepository<Spec, Long> {
}
