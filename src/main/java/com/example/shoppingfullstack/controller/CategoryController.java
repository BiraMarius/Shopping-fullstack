package com.example.shoppingfullstack.controller;

import com.example.shoppingfullstack.entityBody.CategoryBody;
import com.example.shoppingfullstack.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add-category-to-db")
    public String addCategoryToDb(@RequestParam String name){
        categoryService.addCategoryToDb(name);
        return "Category added succesfully";
    }
}
