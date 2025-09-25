package com.example.demo.services;

import com.example.demo.models.Order;
import com.example.demo.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public Order placeOrder(Order order) {
        order.setOrderDate(LocalDate.now());
        double total = order.getProducts().stream()
                .mapToDouble(p -> p.getPrice())
                .sum();
        order.setTotalAmount(total);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
