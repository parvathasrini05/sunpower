package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    public List<Product> getProductsByModel(String model) {
        return productRepository.findByModel(model);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setBrand(productDetails.getBrand());
        product.setModel(productDetails.getModel());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        product.setWarrantyMonths(productDetails.getWarrantyMonths());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    public Product updateStock(Long id, int newStock) {
        Product product = getProductById(id);
        product.setStock(newStock);
        return productRepository.save(product);
    }

    public List<Product> getLowStockProducts(int threshold) {
        return productRepository.findByStockLessThan(threshold);
    }

    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
}