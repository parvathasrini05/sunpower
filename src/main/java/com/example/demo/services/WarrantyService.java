package com.example.demo.services;

import com.example.demo.models.Warranty;
import com.example.demo.models.Order;
import com.example.demo.models.Product;
import com.example.demo.repositories.WarrantyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarrantyService {

    private final WarrantyRepository warrantyRepository;
    private final OrderService orderService;

    public List<Warranty> getAllWarranties() {
        return warrantyRepository.findAll();
    }

    public Warranty getWarrantyById(Long id) {
        return warrantyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warranty not found with id: " + id));
    }

    public Warranty createWarranty(Warranty warranty) {
        Order order = orderService.getOrderById(warranty.getOrder().getId());
        
        // Calculate warranty end date based on product's warranty months
        Product product = order.getProducts().get(0); // Assuming one product per order for simplicity
        LocalDate endDate = warranty.getStartDate().plusMonths(product.getWarrantyMonths());
        warranty.setEndDate(endDate);
        
        return warrantyRepository.save(warranty);
    }

    public Warranty updateWarranty(Long id, Warranty warrantyDetails) {
        Warranty warranty = getWarrantyById(id);
        warranty.setStartDate(warrantyDetails.getStartDate());
        warranty.setEndDate(warrantyDetails.getEndDate());
        return warrantyRepository.save(warranty);
    }

    public void deleteWarranty(Long id) {
        Warranty warranty = getWarrantyById(id);
        warrantyRepository.delete(warranty);
    }

    public List<Warranty> getExpiredWarranties() {
        return warrantyRepository.findByEndDateBefore(LocalDate.now());
    }

    public List<Warranty> getActiveWarranties() {
        return warrantyRepository.findByEndDateAfter(LocalDate.now());
    }

    public Optional<Warranty> getWarrantyByOrderId(Long orderId) {
        return warrantyRepository.findByOrderId(orderId);
    }
}