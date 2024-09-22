package com.example.shoppingfullstack.service;

import com.example.shoppingfullstack.entity.*;
import com.example.shoppingfullstack.entityBody.AddressOfCustomerBody;
import com.example.shoppingfullstack.exception.ThisIsAGeneralException;
import com.example.shoppingfullstack.repository.AddressOfCustomerRepository;
import com.example.shoppingfullstack.repository.CustomerRepository;
import com.example.shoppingfullstack.repository.DeliveryAddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressOfCustomerService {

    private final AddressOfCustomerRepository addressOfCustomerRepository;
    private final CustomerRepository customerRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;

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

    public AddressOfCustomer mappingAddressOfCustomerFromDeliceryAddress(DeliveryAddress deliveryAddress){
        return AddressOfCustomer.builder()
                .country(deliveryAddress.getCountry())
                .county(deliveryAddress.getCounty())
                .city(deliveryAddress.getCity())
                .postalCode(deliveryAddress.getPostalCode())
                .street(deliveryAddress.getStreet())
                .number(deliveryAddress.getNumber())
                .building(deliveryAddress.getBuilding())
                .additionalInfo(deliveryAddress.getAdditionalInfo())
                .build();
    }

    public DeliveryAddress mappingDeliveryAddressFromAddressOfCustomer(AddressOfCustomer addressOfCustomer){
        return DeliveryAddress.builder()
                .country(addressOfCustomer.getCountry())
                .county(addressOfCustomer.getCounty())
                .city(addressOfCustomer.getCity())
                .postalCode(addressOfCustomer.getPostalCode())
                .street(addressOfCustomer.getStreet())
                .number(addressOfCustomer.getNumber())
                .building(addressOfCustomer.getBuilding())
                .additionalInfo(addressOfCustomer.getAdditionalInfo())
                .build();
    }

    public DeliveryAddress checkAddressOfCustomerAndReturnDeliveryAddress(AddressOfCustomer addressOfCustomer) throws RuntimeException{
        if(addressOfCustomer.getId() == null){
            addressOfCustomerRepository.save(addressOfCustomer);
            DeliveryAddress deliveryAddress = mappingDeliveryAddressFromAddressOfCustomer(addressOfCustomer);
            deliveryAddressRepository.save(deliveryAddress);
            return deliveryAddress;
        }
        Optional<AddressOfCustomer> addressOfCustomerOptional = addressOfCustomerRepository.findById(addressOfCustomer.getId());
        if(addressOfCustomerOptional.isPresent()){
            DeliveryAddress deliveryAddress = mappingDeliveryAddressFromAddressOfCustomer(addressOfCustomerOptional.get());
            deliveryAddressRepository.save(deliveryAddress);
            return deliveryAddress;
        }
        throw new ThisIsAGeneralException("Something went wrong. Error: 110");

    }



}
