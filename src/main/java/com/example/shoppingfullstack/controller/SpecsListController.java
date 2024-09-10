package com.example.shoppingfullstack.controller;

import com.example.shoppingfullstack.entityBody.SpecBody;
import com.example.shoppingfullstack.service.SpecListService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SpecsListController {

    private final SpecListService specListService;

    @PostMapping("/add-spec-to-db")
    public String addSpecInDatabase(@RequestBody SpecBody specBody){
        specListService.addSpecToSpecList(specBody);
        return "Spec added.";
    }

}
