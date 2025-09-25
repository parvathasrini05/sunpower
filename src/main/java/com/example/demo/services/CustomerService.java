package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
