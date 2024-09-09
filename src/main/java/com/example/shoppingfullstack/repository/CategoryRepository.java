package com.example.shoppingfullstack.repository;

import com.example.shoppingfullstack.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Optional<Category> findCategoryByNameIgnoreCase(String name);
}
