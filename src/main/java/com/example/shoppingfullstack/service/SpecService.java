package com.example.shoppingfullstack.service;

import com.example.shoppingfullstack.entity.Spec;
import com.example.shoppingfullstack.entityBody.SpecBody;
import com.example.shoppingfullstack.exception.ThisIsAGeneralException;
import com.example.shoppingfullstack.repository.SpecRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpecService {

    private final SpecRepository specRepository;

    public void addSpec(SpecBody specBody){
        Spec spec = verifyIfExists(specBody);
        if(spec == null){
            spec = new Spec(specBody.getSpecName(), specBody.getSpecValue());
            specRepository.save(spec);
        } else {
            throw new ThisIsAGeneralException("Spec already exists.");
        }
    }

    private Spec verifyIfExists(SpecBody specBody){
        return specRepository.findSpecByNameAndValueOfSpecIgnoreCase(specBody.getSpecName(), specBody.getSpecValue());
    }

    protected Spec addOrReturn(SpecBody specBody){
        Spec spec = specRepository.findSpecByNameAndValueOfSpecIgnoreCase(specBody.getSpecName(), specBody.getSpecValue());
        if(spec == null){
            spec = new Spec(specBody.getSpecName(), specBody.getSpecValue());
            specRepository.save(spec);
            return spec;
        } else {
            return spec;
        }
    }

}
