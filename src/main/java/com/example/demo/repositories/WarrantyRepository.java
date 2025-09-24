package com.example.demo.repositories;

<<<<<<< HEAD
import com.example.demo.models.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
    // use nested-property style to reference order's id
    Optional<Warranty> findByOrder_Id(Long orderId);
    List<Warranty> findByEndDateAfter(LocalDate currentDate);
=======
import com.example.demo.models.Warranty;  // Adjust package if needed
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
    Warranty findByOrder_Id(Long orderId); // Use underscore if entity has Order object
>>>>>>> 89bdafad69f44b0c16102f56f1649a325a2f1bd0
}
