package com.example.demo.repositories;

import com.example.demo.models.Warranty;  // Adjust package if needed
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
    Warranty findByOrder_Id(Long orderId); // Use underscore if entity has Order object
}
