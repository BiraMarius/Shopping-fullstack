package com.example.shoppingfullstack.service;

import com.example.shoppingfullstack.entity.Shop;
import com.example.shoppingfullstack.entityBody.ShopBody;
import com.example.shoppingfullstack.repository.ShopRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public void save_open_shop(ShopBody shopBody){
        Shop shop = new Shop(shopBody.getName(), shopBody.getEmail(), shopBody.getTel(), shopBody.getWorkingHours());
        shopRepository.save(shop);
    }

}
