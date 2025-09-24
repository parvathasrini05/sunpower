package com.example.demo.services;

import com.example.demo.models.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {
        customerService.getCustomerById(customerId); // Validate customer exists
        return orderRepository.findByCustomerId(customerId);
    }

    public Order createOrder(Order order) {
        // Validate customer exists
        customerService.getCustomerById(order.getCustomer().getId());
        
        // Validate products exist and have sufficient stock
        if (order.getProducts() != null) {
            order.getProducts().forEach(product -> {
                Product existingProduct = productService.getProductById(product.getId());
                if (existingProduct.getStock() <= 0) {
                    throw new RuntimeException("Product out of stock: " + existingProduct.getBrand() + " " + existingProduct.getModel());
                }
            });
        }
        
        order.setOrderDate(LocalDate.now());
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order orderDetails) {
        Order order = getOrderById(id);
        order.setTotalAmount(orderDetails.getTotalAmount());
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }

    public List<Order> getOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }
}