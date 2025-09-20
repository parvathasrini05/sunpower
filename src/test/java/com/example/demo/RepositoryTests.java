package com.example.demo.repositories;

import com.example.demo.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WarrantyRepository warrantyRepository;

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @Test
    public void testUserRepository() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password");
        
        entityManager.persist(user);
        entityManager.flush();

        Optional<User> found = userRepository.findByUsername("testuser");
        assertTrue(found.isPresent());
        assertEquals("test@example.com", found.get().getEmail());
    }

    @Test
    public void testCustomerRepository() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john@example.com");
        
        entityManager.persist(customer);
        entityManager.flush();

        Optional<Customer> found = customerRepository.findByEmail("john@example.com");
        assertTrue(found.isPresent());
        assertEquals("John", found.get().getFirstName());
    }

    @Test
    public void testProductRepository() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(99.99);
        product.setSku("TEST123");
        
        entityManager.persist(product);
        entityManager.flush();

        Optional<Product> found = productRepository.findBySku("TEST123");
        assertTrue(found.isPresent());
        assertEquals(99.99, found.get().getPrice());
    }

    @Test
    public void testOrderRepository() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        entityManager.persist(customer);

        Order order = new Order();
        order.setCustomer(customer);
        order.setTotalAmount(199.99);
        order.setStatus(Order.OrderStatus.PENDING);
        
        entityManager.persist(order);
        entityManager.flush();

        List<Order> orders = orderRepository.findByCustomerId(customer.getId());
        assertFalse(orders.isEmpty());
        assertEquals(199.99, orders.get(0).getTotalAmount());
    }

    @Test
    public void testWarrantyRepository() {
        Product product = new Product();
        product.setName("Test Product");
        entityManager.persist(product);

        Warranty warranty = new Warranty();
        warranty.setProduct(product);
        warranty.setTerms("Standard warranty");
        
        entityManager.persist(warranty);
        entityManager.flush();

        Optional<Warranty> found = warrantyRepository.findByProductId(product.getId());
        assertTrue(found.isPresent());
        assertEquals("Standard warranty", found.get().getTerms());
    }

    @Test
    public void testServiceRequestRepository() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        entityManager.persist(customer);

        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setCustomer(customer);
        serviceRequest.setDescription("Test request");
        serviceRequest.setStatus(ServiceRequest.ServiceStatus.OPEN);
        
        entityManager.persist(serviceRequest);
        entityManager.flush();

        List<ServiceRequest> requests = serviceRequestRepository.findByCustomerId(customer.getId());
        assertFalse(requests.isEmpty());
        assertEquals("Test request", requests.get(0).getDescription());
    }
}
