package com.example.demo.repositories;

import com.example.demo.models.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
    // use nested-property style to reference order's id
    Optional<Warranty> findByOrder_Id(Long orderId);
    List<Warranty> findByEndDateAfter(LocalDate currentDate);
}
