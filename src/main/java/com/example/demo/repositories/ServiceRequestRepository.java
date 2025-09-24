package com.example.demo.repositories;

import com.example.demo.models.ServiceRequest;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    List<ServiceRequest> findByCustomerId(Long customerId);
    List<ServiceRequest> findByStatus(String status);
}
=======
import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    List<ServiceRequest> findByCustomer(Customer customer);
    List<ServiceRequest> findByProduct(Product product);
    List<ServiceRequest> findByStatus(String status);
}
>>>>>>> b24923907923c92842cc0682f3fdb4e692befb48
