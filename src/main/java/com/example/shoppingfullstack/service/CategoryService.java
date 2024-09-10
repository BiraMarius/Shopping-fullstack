package com.example.shoppingfullstack.service;

import com.example.shoppingfullstack.entity.Category;
import com.example.shoppingfullstack.entity.SpecsList;
import com.example.shoppingfullstack.exception.ThisIsAGeneralException;
import com.example.shoppingfullstack.repository.CategoryRepository;
import com.example.shoppingfullstack.repository.SpecListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SpecListRepository specListRepository;

    public void addCategoryToDb(String name) throws RuntimeException{
        Category category = checkAndReturnIfTheCategoryExists(name);
        if(category==null){
            SpecsList specsList = new SpecsList();
            category = new Category(name, specsList);
            categoryRepository.save(category);
            specsList.setCategory(category);
            specListRepository.save(specsList);
        } else {
            throw new ThisIsAGeneralException("This category and SpecList already exists.");
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

}
