
package com.example.demo.repositories;

import com.example.demo.models.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);
    List<Order> findByCustomer_Id(Long customerId);
}