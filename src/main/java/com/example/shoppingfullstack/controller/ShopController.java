package com.example.shoppingfullstack.controller;

import com.example.shoppingfullstack.entityBody.ShopBody;
import com.example.shoppingfullstack.service.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PostMapping("/add-shop")
    public String addShop(@RequestBody ShopBody shopBody){
        shopService.save_open_shop(shopBody);
        return "Shop saved.";
    }
}
