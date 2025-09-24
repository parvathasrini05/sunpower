package com.example.demo.repositories;

import com.example.demo.models.Warranty;  // Adjust package if needed

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
    Warranty findByOrder_Id(Long orderId); // Use underscore if entity has Order object
        Optional<Warranty> findByOrderId(Long orderId);

    List<Warranty> findByEndDateBefore(LocalDate date);
    List<Warranty> findByEndDateAfter(LocalDate date);

}
