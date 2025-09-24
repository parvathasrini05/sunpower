package com.example.demo.repositories;

import com.example.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBrand(String brand);
    List<Product> findByModel(String model);
    List<Product> findByBrandAndModel(String brand, String model);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByStockLessThan(int threshold);
}