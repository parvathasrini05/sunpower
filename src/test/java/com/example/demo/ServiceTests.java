package com.example.demo.services;

import com.example.demo.models.*;
import com.example.demo.repositories.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private WarrantyRepository warrantyRepository;

    @Mock
    private ServiceRequestRepository serviceRequestRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @InjectMocks
    private CustomerService customerService;

    @InjectMocks
    private ProductService productService;

    @InjectMocks
    private OrderService orderService;

    @InjectMocks
    private WarrantyService warrantyService;

    @InjectMocks
    private ServiceRequestService serviceRequestService;

    @Test
    public void testUserService() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        User savedUser = userService.createUser(user, "password");
        assertNotNull(savedUser);

        Optional<User> foundUser = userService.getUserById(1L);
        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
    }

    @Test
    public void testCustomerService() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setEmail("john@example.com");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(customerRepository.findByEmail("john@example.com")).thenReturn(Optional.of(customer));

        Customer savedCustomer = customerService.createCustomer(customer);
        assertNotNull(savedCustomer);

        Optional<Customer> foundCustomer = customerService.getCustomerByEmail("john@example.com");
        assertTrue(foundCustomer.isPresent());
        assertEquals("John", foundCustomer.get().getFirstName());
    }

    @Test
    public void testProductService() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(99.99);

        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productRepository.findAll()).thenReturn(List.of(product));

        Product savedProduct = productService.createProduct(product);
        assertNotNull(savedProduct);

        List<Product> products = productService.getAllProducts();
        assertFalse(products.isEmpty());
        assertEquals("Test Product", products.get(0).getName());
    }

    @Test
    public void testOrderService() {
        Customer customer = new Customer();
        customer.setId(1L);

        Order order = new Order();
        order.setId(1L);
        order.setCustomer(customer);
        order.setTotalAmount(199.99);

        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(orderRepository.findByCustomerId(1L)).thenReturn(List.of(order));

        Order savedOrder = orderService.createOrder(order);
        assertNotNull(savedOrder);

        List<Order> orders = orderService.getOrdersByCustomerId(1L);
        assertFalse(orders.isEmpty());
        assertEquals(199.99, orders.get(0).getTotalAmount());
    }

    @Test
    public void testWarrantyService() {
        Product product = new Product();
        product.setId(1L);

        Warranty warranty = new Warranty();
        warranty.setId(1L);
        warranty.setProduct(product);
        warranty.setTerms("Standard warranty");

        when(warrantyRepository.save(any(Warranty.class))).thenReturn(warranty);
        when(warrantyRepository.findByProductId(1L)).thenReturn(Optional.of(warranty));

        Warranty savedWarranty = warrantyService.createWarranty(warranty);
        assertNotNull(savedWarranty);

        Optional<Warranty> foundWarranty = warrantyService.getWarrantyByProductId(1L);
        assertTrue(foundWarranty.isPresent());
        assertEquals("Standard warranty", foundWarranty.get().getTerms());
    }

    @Test
    public void testServiceRequestService() {
        Customer customer = new Customer();
        customer.setId(1L);

        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setId(1L);
        serviceRequest.setCustomer(customer);
        serviceRequest.setDescription("Test request");

        when(serviceRequestRepository.save(any(ServiceRequest.class))).thenReturn(serviceRequest);
        when(serviceRequestRepository.findByCustomerId(1L)).thenReturn(List.of(serviceRequest));

        ServiceRequest savedRequest = serviceRequestService.createServiceRequest(serviceRequest);
        assertNotNull(savedRequest);

        List<ServiceRequest> requests = serviceRequestService.getServiceRequestsByCustomerId(1L);
        assertFalse(requests.isEmpty());
        assertEquals("Test request", requests.get(0).getDescription());
    }
}
