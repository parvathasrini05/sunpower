package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String model;

    private double price;

    private int stock;

    private int warrantyMonths; // warranty period in months

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ServiceRequest> serviceRequests;
}