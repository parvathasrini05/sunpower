package com.example.demo.repositories;

<<<<<<< HEAD
import com.example.demo.models.Order;
import com.example.demo.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(Customer customer);
    List<Order> findByOrderDateBetween(LocalDate start, LocalDate end);
=======
import com.example.demo.models.Order;  // Make sure this matches your entity
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer_Id(Long customerId); // if Order has Customer object
>>>>>>> 89bdafad69f44b0c16102f56f1649a325a2f1bd0
}
