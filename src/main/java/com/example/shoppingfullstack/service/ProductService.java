package com.example.shoppingfullstack.service;

import com.example.shoppingfullstack.entity.Product;
import com.example.shoppingfullstack.entity.Spec;
import com.example.shoppingfullstack.entityBody.ProductBody;
import com.example.shoppingfullstack.entityBody.SpecBody;
import com.example.shoppingfullstack.exception.ThisIsAGeneralException;
import com.example.shoppingfullstack.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final SpecService specService;

    public void addProductToDb(ProductBody productBody){
        Product product = checkProduct(productBody);
        if(product == null){
            Set<Spec> specs = new HashSet<>();
//            Set<SpecBody> specTest2 = productBody.getSpecifications();
//            for(SpecBody specBody : productBody.getSpecifications()){
//                specTest2.add(specBody);
//            }
            Set<SpecBody> specTest = productBody.getSpecifications();
            for(SpecBody spec : specTest){

                SpecBody specBody = new SpecBody(spec.getSpecName(), spec.getSpecValue());
                specs.add(specService.addOrReturn(specBody));
            }
            product = new Product(categoryService.addOrReturn(productBody.getCategoryName()), specs, productBody.getName(), productBody.getBrand(), productBody.getDescription(), productBody.getPrice(), productBody.getShortDescription(), productBody.getBarcode(), LocalDate.now());
            productRepository.save(product);
        } else {
            throw new ThisIsAGeneralException("Product already exists.");
        }
    }



    private Product checkProduct(ProductBody productBody){
        return productRepository.findProductByBarcode(productBody.getBarcode());
    }

}
