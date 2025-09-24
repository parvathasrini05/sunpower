package com.example.demo.repositories;

import com.example.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Repository;
import java.util.List;
>>>>>>> 89bdafad69f44b0c16102f56f1649a325a2f1bd0

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBrand(String brand);
<<<<<<< HEAD
    List<Product> findByModelContainingIgnoreCase(String model);
    List<Product> findByStockLessThan(int stock);
}
=======
    List<Product> findByModel(String model);
    List<Product> findByBrandAndModel(String brand, String model);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByStockLessThan(int threshold);
}
>>>>>>> 89bdafad69f44b0c16102f56f1649a325a2f1bd0
