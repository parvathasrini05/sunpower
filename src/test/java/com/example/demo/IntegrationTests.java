package com.example.demo;

import com.example.demo.controllers.CustomerController;
import com.example.demo.models.Customer;
import com.example.demo.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IntegrationTests {

    @Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerService customerService;

    @Test
    public void contextLoads() {
        assertNotNull(customerController);
    }

    @Test
    public void testCustomerIntegration() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        
        when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);
        when(customerService.getCustomerById(1L)).thenReturn(Optional.of(customer));

        assertNotNull(customerController.createCustomer(customer));
        assertNotNull(customerController.getCustomerById(1L));
    }
}
