package com.Safehealthtrack.services;

import com.Safehealthtrack.models.Metrics;
import com.Safehealthtrack.repositories.MetricsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricsService {
    private final MetricsRepository metricsRepository;

    public MetricsService(MetricsRepository metricsRepository) {
        this.metricsRepository = metricsRepository;
    }

    public void saveMetrics(Metrics metrics) {
        // Business logic: validate metrics
        validateMetrics(metrics);
        metricsRepository.save(metrics);
    }

    public List<Metrics> getMetricsByUserId(String userId) {
        return metricsRepository.findByUserId(userId);
    }

    public Double getAverageWeight(String userId) {
        return metricsRepository.getAverageWeight(userId);
    }

    public Double getAverageBloodSugar(String userId) {
        return metricsRepository.getAverageBloodSugar(userId);
    }

    private void validateMetrics(Metrics metrics) {
        if (metrics.getWeight() <= 0 || metrics.getWeight() > 300) {
            throw new IllegalArgumentException("Invalid weight value");
        }
        if (metrics.getHeartRate() < 40 || metrics.getHeartRate() > 200) {
            throw new IllegalArgumentException("Invalid heart rate value");
        }
        if (metrics.getBloodSugar() < 50 || metrics.getBloodSugar() > 500) {
            throw new IllegalArgumentException("Invalid blood sugar value");
        }
    }

    public String analyzeBloodPressure(String bloodPressure) {
        String[] values = bloodPressure.split("/");
        int systolic = Integer.parseInt(values[0]);
        int diastolic = Integer.parseInt(values[1]);

        if (systolic < 90 || diastolic < 60) {
            return "Low blood pressure";
        } else if (systolic > 120 || diastolic > 80) {
            return "High blood pressure";
        } else {
            return "Normal blood pressure";
        }
    }
}
