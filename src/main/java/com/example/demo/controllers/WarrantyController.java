package com.example.demo.controllers;

import com.example.demo.models.Warranty;
import com.example.demo.services.WarrantyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warranties")
@RequiredArgsConstructor
public class WarrantyController {

    private final WarrantyService warrantyService;

    @PostMapping("/create")
    public Warranty createWarranty(@RequestBody Warranty warranty) {
        return warrantyService.createWarranty(warranty);
    }

    @GetMapping("/all")
    public List<Warranty> getAllWarranties() {
        return warrantyService.getAllWarranties();
    }
}
