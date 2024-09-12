package com.example.shoppingfullstack.service;

import com.example.shoppingfullstack.entity.AddressOfCustomer;
import com.example.shoppingfullstack.entity.Customer;
import com.example.shoppingfullstack.entityBody.AddressOfCustomerBody;
import com.example.shoppingfullstack.exception.ThisIsAGeneralException;
import com.example.shoppingfullstack.repository.AddressOfCustomerRepository;
import com.example.shoppingfullstack.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressOfCustomerService {

    private final AddressOfCustomerRepository addressOfCustomerRepository;
    private final CustomerRepository customerRepository;

    public void addAddressOfCustomer(AddressOfCustomerBody addressOfCustomerBody) throws RuntimeException{
        Customer customer = customerRepository.findCustomerByEmailIgnoreCase(addressOfCustomerBody.getCustomerEmail());
        checkNotNullAtributes(addressOfCustomerBody);
        if(customer != null){
            AddressOfCustomer addressOfCustomer = new AddressOfCustomer(customer, addressOfCustomerBody.getCountry(),
                    addressOfCustomerBody.getCounty(), addressOfCustomerBody.getCity(), addressOfCustomerBody.getPostalCode(),
                    addressOfCustomerBody.getStreet(), addressOfCustomerBody.getNumber(), addressOfCustomerBody.getBuilding(),
                    addressOfCustomerBody.getAdditionalInfo());
            addressOfCustomerRepository.save(addressOfCustomer);
        } else {
            throw new ThisIsAGeneralException("Customer not found.");
        }
    }

    private void checkNotNullAtributes(AddressOfCustomerBody addressOfCustomerBody) throws RuntimeException{
        validateField("Email",addressOfCustomerBody.getCustomerEmail());
        validateField("Country",addressOfCustomerBody.getCountry());
        validateField("County",addressOfCustomerBody.getCounty());
        validateField("City",addressOfCustomerBody.getCity());
        validateField("Postal code",addressOfCustomerBody.getPostalCode());
        validateField("Street",addressOfCustomerBody.getStreet());
        validateField("Number",addressOfCustomerBody.getNumber());
//        if(addressOfCustomerBody.getBuilding() == null || addressOfCustomerBody.getBuilding().isEmpty()){
//
//        }else if(addressOfCustomerBody.getAdditionalInfo() == null || addressOfCustomerBody.getAdditionalInfo().isEmpty()){
//
//        }
    }


    private void validateField(String fieldName, String fieldValue){
        if(fieldValue == null || fieldValue.isEmpty()){
            throw new ThisIsAGeneralException(fieldName+" is empty.");
        }
    }

}
