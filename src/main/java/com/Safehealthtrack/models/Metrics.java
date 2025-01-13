package com.Safehealthtrack.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "metrics")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Metrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private Double weight; // in kilograms
    private String bloodPressure; // e.g., "120/80"
    private Integer heartRate; // in bpm
    private Double bloodSugar; // in mg/dL
    private Double cholesterol; // in mg/dL
    private String symptoms;
    private LocalDateTime timestamp = LocalDateTime.now();
}
