package com.example.demo.controllers;

import com.example.demo.models.Warranty;
import com.example.demo.services.WarrantyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/warranties")
@RequiredArgsConstructor
public class WarrantyController {

    private final WarrantyService warrantyService;

    @GetMapping
    public List<Warranty> getAllWarranties() {
        return warrantyService.getAllWarranties();
    }

    @GetMapping("/{id}")
    public Warranty getWarrantyById(@PathVariable Long id) {
        return warrantyService.getWarrantyById(id);
    }

    @GetMapping("/expired")
    public List<Warranty> getExpiredWarranties() {
        return warrantyService.getExpiredWarranties();
    }

    @GetMapping("/active")
    public List<Warranty> getActiveWarranties() {
        return warrantyService.getActiveWarranties();
    }

    @GetMapping("/order/{orderId}")
    public Optional<Warranty> getWarrantyByOrderId(@PathVariable Long orderId) {
        return warrantyService.getWarrantyByOrderId(orderId);
    }

    @PostMapping
    public Warranty createWarranty(@RequestBody Warranty warranty) {
        return warrantyService.createWarranty(warranty);
    }

    @PutMapping("/{id}")
    public Warranty updateWarranty(@PathVariable Long id, @RequestBody Warranty warranty) {
        return warrantyService.updateWarranty(id, warranty);
    }

    @DeleteMapping("/{id}")
    public void deleteWarranty(@PathVariable Long id) {
        warrantyService.deleteWarranty(id);
    }
}
