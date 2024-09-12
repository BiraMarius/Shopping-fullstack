package com.example.shoppingfullstack.controller;

import com.example.shoppingfullstack.entityBody.SpecBody;
import com.example.shoppingfullstack.service.SpecService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SpecController {
    private final SpecService specService;

    @PostMapping("/add-spec")
    public String addSpec(@RequestBody SpecBody specBody){
        specService.addSpec(specBody);
        return "Spec added to database.";
    }
}
