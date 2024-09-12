package com.example.shoppingfullstack.controller;

import com.example.shoppingfullstack.entityBody.ProductBody;
import com.example.shoppingfullstack.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add-product")
    public String addProduct(@RequestBody ProductBody productBody){
        productService.addProductToDb(productBody);
        return "Product added.";
    }
}
