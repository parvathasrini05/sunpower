package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ServiceRequest;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceRequest, Long> {
    List<ServiceRequest> findByCustomer_Id(Long customerId);
}
