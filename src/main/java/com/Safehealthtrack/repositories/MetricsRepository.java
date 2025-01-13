package com.Safehealthtrack.repositories;

import com.Safehealthtrack.models.Metrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MetricsRepository extends JpaRepository<Metrics, Long> {

    List<Metrics> findByUserId(String userId);

    @Query("SELECT AVG(m.weight) FROM Metrics m WHERE m.userId = :userId")
    Double getAverageWeight(String userId);

    @Query("SELECT AVG(m.bloodSugar) FROM Metrics m WHERE m.userId = :userId")
    Double getAverageBloodSugar(String userId);
}
