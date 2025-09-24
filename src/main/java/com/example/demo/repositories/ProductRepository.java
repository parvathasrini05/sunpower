package com.example.demo.repositories;

import com.example.demo.models.Product; // Correct package

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
        List<Product> findByBrand(String brand);
        List<Product> findByModel(String model);
        List<Product> findByStockLessThan(int threshold);


    List<Product> findByPriceBetween(double minPrice, double maxPrice);
}
    
