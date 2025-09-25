package com.example.demo.services;

import com.example.demo.models.Warranty;
import com.example.demo.repositories.WarrantyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WarrantyService {

    private final WarrantyRepository warrantyRepository;

    public Warranty createWarranty(Warranty warranty) {
        warranty.setStartDate(LocalDate.now());
        warranty.getProducts().forEach(p -> {
            LocalDate endDate = warranty.getStartDate().plusMonths(p.getWarrantyMonths());
            warranty.setEndDate(endDate);
        });
        return warrantyRepository.save(warranty);
    }

    public List<Warranty> getAllWarranties() {
        return warrantyRepository.findAll();
    }
}
