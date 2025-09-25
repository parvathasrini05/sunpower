package com.example.demo.repositories;

import com.example.demo.models.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
}
