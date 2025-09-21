package com.example.demo;

import com.example.demo.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {
    CustomerController.class,
    OrderController.class,
    ProductController.class,
    ServiceRequestController.class,
    UserController.class,
    WarrantyController.class
})
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private ProductService productService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private WarrantyService warrantyService;

    @MockBean
    private ServiceRequestService serviceRequestService;

    @Test
    public void testUserController() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");

        when(userService.createUser(any(User.class), anyString())).thenReturn(user);
        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testuser\",\"email\":\"test@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("testuser"));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    public void testCustomerController() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setEmail("john@example.com");

        when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);
        when(customerService.getCustomerById(1L)).thenReturn(Optional.of(customer));

        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"));

        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    public void testProductController() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(99.99);

        when(productService.createProduct(any(Product.class))).thenReturn(product);
        when(productService.getAllProducts()).thenReturn(List.of(product));

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Product"));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].price").value(99.99));
    }

    @Test
    public void testOrderController() throws Exception {
        Order order = new Order();
        order.setId(1L);
        order.setTotalAmount(199.99);

        when(orderService.createOrder(any(Order.class))).thenReturn(order);
        when(orderService.getOrderById(1L)).thenReturn(Optional.of(order));

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.totalAmount").value(199.99));

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalAmount").value(199.99));
    }

    @Test
    public void testWarrantyController() throws Exception {
        Warranty warranty = new Warranty();
        warranty.setId(1L);
        warranty.setTerms("Standard warranty");

        when(warrantyService.createWarranty(any(Warranty.class))).thenReturn(warranty);
        when(warrantyService.getWarrantyById(1L)).thenReturn(Optional.of(warranty));

        mockMvc.perform(post("/api/warranties")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(warranty)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.terms").value("Standard warranty"));

        mockMvc.perform(get("/api/warranties/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.terms").value("Standard warranty"));
    }

    @Test
    public void testServiceRequestController() throws Exception {
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setId(1L);
        serviceRequest.setDescription("Test request");

        when(serviceRequestService.createServiceRequest(any(ServiceRequest.class))).thenReturn(serviceRequest);
        when(serviceRequestService.getServiceRequestById(1L)).thenReturn(Optional.of(serviceRequest));

        mockMvc.perform(post("/api/service-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(serviceRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description").value("Test request"));

        mockMvc.perform(get("/api/service-requests/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Test request"));
    }
}
