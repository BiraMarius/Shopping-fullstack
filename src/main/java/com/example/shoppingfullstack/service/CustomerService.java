package com.example.shoppingfullstack.service;

import com.example.shoppingfullstack.entity.Customer;
import com.example.shoppingfullstack.entityBody.CustomerBody;
import com.example.shoppingfullstack.exception.ThisIsAGeneralException;
import com.example.shoppingfullstack.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public void addCustomer(CustomerBody customerBody) throws RuntimeException{
        Customer customer = checkIfCustomerExists(customerBody);
        if(customer == null){
            customer = new Customer(customerBody.getFirstName(), customerBody.getLastName(), customerBody.getPhone(), customerBody.getEmail(), LocalDate.now());
            customerRepository.save(customer);
        } else {
            throw new ThisIsAGeneralException("Customer exists in database.");
        }
    }

    public Customer checkIfCustomerExists(CustomerBody customerBody) throws RuntimeException{
        Customer customer = customerRepository.findCustomerByFirstNameAndLastNameAndEmailAndPhone(customerBody.getFirstName(),
                customerBody.getLastName(), customerBody.getEmail(), customerBody.getPhone());
        Customer customerCheckByEmail = customerRepository.findCustomerByEmailIgnoreCase(customerBody.getEmail());
        Customer customerCheckByPhone = customerRepository.findCustomerByPhone(customerBody.getPhone());
        if(customer != null){
            return customer;
        } else if(customerCheckByEmail!=null || customerCheckByPhone!=null){
            throw new ThisIsAGeneralException("Customer exists in database.");
        } else {
            return null;
        }
    }

    public Customer findCustomer(Long customerId) throws RuntimeException{
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent()){
            return customerOptional.get();
        } else {
            throw new ThisIsAGeneralException("Customer not found. Error:101");
        }
    }
}
