package com.example.demo.services;

import com.example.demo.models.ServiceRequest;
import com.example.demo.repositories.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceRequestService {

    private final ServiceRepository serviceRequestRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    public List<ServiceRequest> getAllServiceRequests() {
        return serviceRequestRepository.findAll();
    }

    public ServiceRequest getServiceRequestById(Long id) {
        return serviceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service request not found with id: " + id));
    }

    public List<ServiceRequest> getServiceRequestsByCustomerId(Long customerId) {
        customerService.getCustomerById(customerId); // Validate customer exists
        return serviceRequestRepository.findByCustomerId(customerId);
    }

    public List<ServiceRequest> getServiceRequestsByStatus(String status) {
        return serviceRequestRepository.findAll()
            .stream()
            .filter(sr -> status.equals(sr.getStatus()))
            .toList();
    }

    public ServiceRequest createServiceRequest(ServiceRequest serviceRequest) {
        // Validate customer exists
        customerService.getCustomerById(serviceRequest.getCustomer().getId());
        
        // Validate product exists
        if (serviceRequest.getProduct() != null) {
            productService.getProductById(serviceRequest.getProduct().getId());
        }
        
        serviceRequest.setRequestDate(LocalDate.now());
        serviceRequest.setStatus(ServiceRequest.ServiceStatus.PENDING);
        return serviceRequestRepository.save(serviceRequest);
    }

    public ServiceRequest updateServiceRequestStatus(Long id, String status) {
        ServiceRequest serviceRequest = getServiceRequestById(id);
        ServiceRequest.ServiceStatus serviceStatus;
        try {
            serviceStatus = ServiceRequest.ServiceStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status value: " + status);
        }
        serviceRequest.setStatus(serviceStatus);
        return serviceRequestRepository.save(serviceRequest);
    }

    public ServiceRequest updateServiceRequest(Long id, ServiceRequest serviceRequestDetails) {
        ServiceRequest serviceRequest = getServiceRequestById(id);
        serviceRequest.setDescription(serviceRequestDetails.getDescription());
        serviceRequest.setStatus(serviceRequestDetails.getStatus());
        return serviceRequestRepository.save(serviceRequest);
    }

    public void deleteServiceRequest(Long id) {
        ServiceRequest serviceRequest = getServiceRequestById(id);
        serviceRequestRepository.delete(serviceRequest);
    }
}