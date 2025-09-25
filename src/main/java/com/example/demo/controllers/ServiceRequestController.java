package com.example.demo.controllers;

import com.example.demo.models.ServiceRequest;
import com.example.demo.services.ServiceRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-requests")
@RequiredArgsConstructor
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;

    @GetMapping
    public List<ServiceRequest> getAllServiceRequests() {
        return serviceRequestService.getAllServiceRequests();
    }

    @GetMapping("/{id}")
    public ServiceRequest getServiceRequestById(@PathVariable Long id) {
        return serviceRequestService.getServiceRequestById(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<ServiceRequest> getServiceRequestsByCustomer(@PathVariable Long customerId) {
        return serviceRequestService.getServiceRequestsByCustomerId(customerId);
    }

    @GetMapping("/status/{status}")
    public List<ServiceRequest> getServiceRequestsByStatus(@PathVariable String status) {
        return serviceRequestService.getServiceRequestsByStatus(status);
    }

    @PostMapping
    public ServiceRequest createServiceRequest(@RequestBody ServiceRequest serviceRequest) {
        return serviceRequestService.createServiceRequest(serviceRequest);
    }

    @PutMapping("/{id}/status")
    public ServiceRequest updateStatus(@PathVariable Long id, @RequestParam String status) {
        return serviceRequestService.updateServiceRequestStatus(id, status);
    }

    @PutMapping("/{id}")
    public ServiceRequest updateServiceRequest(@PathVariable Long id, @RequestBody ServiceRequest serviceRequest) {
        return serviceRequestService.updateServiceRequest(id, serviceRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteServiceRequest(@PathVariable Long id) {
        serviceRequestService.deleteServiceRequest(id);
    }
}