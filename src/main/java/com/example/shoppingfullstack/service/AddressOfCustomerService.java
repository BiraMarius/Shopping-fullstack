package com.example.shoppingfullstack.service;

import com.example.shoppingfullstack.entity.AdressOfCustomer;
import com.example.shoppingfullstack.entity.Customer;
import com.example.shoppingfullstack.entityBody.AdressOfCustomerBody;
import com.example.shoppingfullstack.exception.ThisIsAGeneralException;
import com.example.shoppingfullstack.repository.AdressOfCustomerRepository;
import com.example.shoppingfullstack.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdressOfCustomerService {

    private final AdressOfCustomerRepository adressOfCustomerRepository;
    private final CustomerRepository customerRepository;

    public void addAdressOfCustomer(AdressOfCustomerBody adressOfCustomerBody) throws RuntimeException{
        Customer customer = customerRepository.findCustomerByEmailIgnoreCase(adressOfCustomerBody.getCustomerEmail());
        checkNotNullAtributes(adressOfCustomerBody);
        if(customer != null){
            AdressOfCustomer adressOfCustomer = new AdressOfCustomer(customer, adressOfCustomerBody.getCountry(),
                    adressOfCustomerBody.getCounty(), adressOfCustomerBody.getCity(), adressOfCustomerBody.getPostalCode(),
                    adressOfCustomerBody.getStreet(), adressOfCustomerBody.getNumber(), adressOfCustomerBody.getBuilding(),
                    adressOfCustomerBody.getAdditionalInfo());
            adressOfCustomerRepository.save(adressOfCustomer);
        } else {
            throw new ThisIsAGeneralException("Customer not found.");
        }
    }

    private void checkNotNullAtributes(AdressOfCustomerBody adressOfCustomerBody) throws RuntimeException{
        validateField("Email",adressOfCustomerBody.getCustomerEmail());
        validateField("Country",adressOfCustomerBody.getCountry());
        validateField("County",adressOfCustomerBody.getCounty());
        validateField("City",adressOfCustomerBody.getCity());
        validateField("Postal code",adressOfCustomerBody.getPostalCode());
        validateField("Street",adressOfCustomerBody.getStreet());
        validateField("Number",adressOfCustomerBody.getNumber());
//        if(adressOfCustomerBody.getBuilding() == null || adressOfCustomerBody.getBuilding().isEmpty()){
//
//        }else if(adressOfCustomerBody.getAdditionalInfo() == null || adressOfCustomerBody.getAdditionalInfo().isEmpty()){
//
//        }
    }


    private void validateField(String fieldName, String fieldValue){
        if(fieldValue == null || fieldValue.isEmpty()){
            throw new ThisIsAGeneralException(fieldName+" is empty.");
        }
    }

}
