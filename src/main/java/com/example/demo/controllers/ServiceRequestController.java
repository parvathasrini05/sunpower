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

    @PostMapping("/create")
    public ServiceRequest createRequest(@RequestBody ServiceRequest request) {
        return serviceRequestService.createRequest(request);
    }

    @GetMapping("/all")
    public List<ServiceRequest> getAllRequests() {
        return serviceRequestService.getAllRequests();
    }
}
