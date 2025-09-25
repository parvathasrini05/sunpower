package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Product product;

    private String description;

    @Enumerated(EnumType.STRING)
    private ServiceStatus status;

    private LocalDate requestDate;

    public enum ServiceStatus {
        PENDING, IN_PROGRESS, COMPLETED
    }
}
