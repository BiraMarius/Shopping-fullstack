package com.example.shoppingfullstack.service;

import com.example.shoppingfullstack.entity.Category;
import com.example.shoppingfullstack.exception.ThisIsAGeneralException;
import com.example.shoppingfullstack.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void addCategoryToDb(String name) throws RuntimeException{
        Category category = checkAndReturnIfTheCategoryExists(name);
        if(category==null){
            category = new Category(name);
            categoryRepository.save(category);
        } else {
            throw new ThisIsAGeneralException("This category already exists in database.");
        }
    }

    protected Category checkAndReturnIfTheCategoryExists(String name){
        Optional<Category> categoryOpt = categoryRepository.findCategoryByNameIgnoreCase(name);
        if(categoryOpt.isPresent()){
            return categoryOpt.get();
        } else {
            return null;
        }
    }

    protected Category addOrReturn(String name){
        Optional<Category> categoryOpt = categoryRepository.findCategoryByNameIgnoreCase(name);
        if(categoryOpt.isPresent()){
            return categoryOpt.get();
        } else {
            Category category = new Category(name);
            categoryRepository.save(category);
            return category;
        }
    }

}
