package com.example.demo.services;

import com.example.demo.models.ServiceRequest;
import com.example.demo.repositories.ServiceRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceRequestService {

    private final ServiceRequestRepository repository;

    public ServiceRequest createRequest(ServiceRequest request) {
        request.setRequestDate(LocalDate.now());
        request.setStatus(ServiceRequest.ServiceStatus.PENDING);
        return repository.save(request);
    }

    public List<ServiceRequest> getAllRequests() {
        return repository.findAll();
    }
}
