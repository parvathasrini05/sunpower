package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/brand/{brand}")
    public List<Product> getProductsByBrand(@PathVariable String brand) {
        return productService.getProductsByBrand(brand);
    }

    @GetMapping("/model/{model}")
    public List<Product> getProductsByModel(@PathVariable String model) {
        return productService.getProductsByModel(model);
    }

    @GetMapping("/lowstock/{threshold}")
    public List<Product> getLowStockProducts(@PathVariable int threshold) {
        return productService.getLowStockProducts(threshold);
    }

    @GetMapping("/price")
    public List<Product> getProductsByPriceRange(@RequestParam double min, @RequestParam double max) {
        return productService.getProductsByPriceRange(min, max);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}/stock")
    public Product updateStock(@PathVariable Long id, @RequestParam int stock) {
        return productService.updateStock(id, stock);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
