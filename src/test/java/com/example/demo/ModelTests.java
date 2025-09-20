package com.example.demo.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ModelTests {

    @Test
    public void testUserEntity() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password");
        
        Role role = new Role();
        role.setName("ROLE_USER");
        user.setRoles(Set.of(role));
        
        assertEquals(1L, user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertTrue(user.getRoles().contains(role));
    }

    @Test
    public void testCustomerEntity() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john@example.com");
        customer.setPhone("123-456-7890");
        
        assertEquals("John Doe", customer.getFullName());
        assertEquals("john@example.com", customer.getEmail());
    }

    @Test
    public void testProductEntity() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(99.99);
        product.setDescription("Test Description");
        product.setSku("TEST123");
        
        assertEquals("Test Product", product.getName());
        assertEquals(99.99, product.getPrice());
    }

    @Test
    public void testOrderEntity() {
        Order order = new Order();
        order.setId(1L);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(199.99);
        order.setStatus(Order.OrderStatus.PENDING);
        
        Customer customer = new Customer();
        customer.setId(1L);
        order.setCustomer(customer);
        
        assertEquals(Order.OrderStatus.PENDING, order.getStatus());
        assertEquals(199.99, order.getTotalAmount());
    }

    @Test
    public void testWarrantyEntity() {
        Warranty warranty = new Warranty();
        warranty.setId(1L);
        warranty.setStartDate(LocalDateTime.now());
        warranty.setEndDate(LocalDateTime.now().plusYears(1));
        warranty.setTerms("Standard warranty terms");
        
        Product product = new Product();
        product.setId(1L);
        warranty.setProduct(product);
        
        assertNotNull(warranty.getStartDate());
        assertNotNull(warranty.getEndDate());
    }

    @Test
    public void testServiceRequestEntity() {
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setId(1L);
        serviceRequest.setDescription("Test service request");
        serviceRequest.setStatus(ServiceRequest.ServiceStatus.OPEN);
        serviceRequest.setCreatedDate(LocalDateTime.now());
        
        Customer customer = new Customer();
        customer.setId(1L);
        serviceRequest.setCustomer(customer);
        
        assertEquals("Test service request", serviceRequest.getDescription());
        assertEquals(ServiceRequest.ServiceStatus.OPEN, serviceRequest.getStatus());
    }

    @Test
    public void testRoleEntity() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_ADMIN");
        
        User user = new User();
        user.setId(1L);
        role.setUsers(Set.of(user));
        
        assertEquals("ROLE_ADMIN", role.getName());
        assertTrue(role.getUsers().contains(user));
    }
}
