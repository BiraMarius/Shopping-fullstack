package com.example.shoppingfullstack.service;

import com.example.shoppingfullstack.entity.CustomerContact;
import com.example.shoppingfullstack.repository.CustomerContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerContactService {
    private final CustomerContactRepository customerContactRepository;


}
