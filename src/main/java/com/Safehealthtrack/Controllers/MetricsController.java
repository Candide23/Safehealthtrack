package com.Safehealthtrack.Controllers;

import com.Safehealthtrack.models.Metrics;
import com.Safehealthtrack.services.MetricsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/metrics")
public class MetricsController {
    private final MetricsService metricsService;

    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @PostMapping
    public ResponseEntity<String> logMetrics(@RequestBody Metrics metrics) {
        metricsService.saveMetrics(metrics);
        return ResponseEntity.ok("Metrics logged successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Metrics>> getMetrics(@PathVariable String userId) {
        List<Metrics> metrics = metricsService.getMetricsByUserId(userId);
        return ResponseEntity.ok(metrics);
    }

    @GetMapping("/{userId}/summary")
    public ResponseEntity<Map<String, Object>> getMetricsSummary(@PathVariable String userId) {
        Double avgWeight = metricsService.getAverageWeight(userId);
        Double avgBloodSugar = metricsService.getAverageBloodSugar(userId);
        return ResponseEntity.ok(Map.of(
                "averageWeight", avgWeight,
                "averageBloodSugar", avgBloodSugar
        ));
    }

    @GetMapping("/analyze/bloodPressure")
    public ResponseEntity<String> analyzeBloodPressure(@RequestParam String bloodPressure) {
        String analysis = metricsService.analyzeBloodPressure(bloodPressure);
        return ResponseEntity.ok(analysis);
    }
}
