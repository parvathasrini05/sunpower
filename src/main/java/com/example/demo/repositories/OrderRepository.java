package com.example.demo.repositories;

import com.example.demo.models.Order;
import com.example.demo.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(Customer customer);
    List<Order> findByOrderDateBetween(LocalDate start, LocalDate end);
}
