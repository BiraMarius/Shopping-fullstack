package com.example.shoppingfullstack.service;

import com.example.shoppingfullstack.entity.Category;
import com.example.shoppingfullstack.entity.Spec;
import com.example.shoppingfullstack.entity.SpecsList;
import com.example.shoppingfullstack.entityBody.SpecBody;
import com.example.shoppingfullstack.exception.ThisIsAGeneralException;
import com.example.shoppingfullstack.repository.SpecListRepository;
import com.example.shoppingfullstack.repository.SpecRepository;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpecListService {

    private final SpecListRepository specListRepository;
    private final SpecRepository specRepository;
    private final CategoryService categoryService;

    public void addSpecToSpecList(SpecBody specBody){
        SpecsList specsList = findSpecListByName(specBody.getCategoryName());
        Spec spec = new Spec(specsList, specBody.getName(), specBody.getValueOfSpec());
        specRepository.save(spec);
    }

    private SpecsList findSpecListByName(String nameOfCategory) throws RuntimeException{
        Category category = categoryService.checkAndReturnIfTheCategoryExists(nameOfCategory);
        if(category != null){
            return category.getSpecs();
        } else {
            throw new ThisIsAGeneralException("No category found with this name.");
        }
    }


}
