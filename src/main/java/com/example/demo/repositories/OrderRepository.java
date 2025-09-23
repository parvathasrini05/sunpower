package com.example.demo.repositories;

import com.example.demo.models.Order;  // Make sure this matches your entity
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer_Id(Long customerId); // if Order has Customer object
}
