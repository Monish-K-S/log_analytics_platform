package com.monish.processing.controller;

import com.monish.processing.analytics.LogAnalyticsService;
import com.monish.processing.entity.LogEntity;
import com.monish.processing.repository.LogRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    private final LogRepository logRepository;
    private final LogAnalyticsService logAnalyticsService;

    public LogController(
        LogRepository logRepository,
        LogAnalyticsService logAnalyticsService
    ) {
        this.logRepository = logRepository;
        this.logAnalyticsService = logAnalyticsService;
    }

    @GetMapping("/logs")
    public Page<LogEntity> getAllLogs(Pageable pageable) {
        return logRepository.findAll(pageable);
    }

    @GetMapping("/logs/recent/{count}")
    public List<LogEntity> getAllByOrderByTimestampDesc(
        @PathVariable int count
    ) {
        Pageable pageable = PageRequest.of(0, count);
        return logRepository.findAllByOrderByTimestampDesc(pageable);
    }

    @GetMapping("/logs/since/{minutes}")
    public List<LogEntity> getLogsByTimestampAfter(@PathVariable int minutes) {
        LocalDateTime cutoff = LocalDateTime.now().minusMinutes(minutes);
        return logRepository.findByTimestampAfter(cutoff);
    }

    @GetMapping("/logs/level/{level}")
    public List<LogEntity> getLogsByLevel(@PathVariable String level) {
        return logRepository.findByLevel(level);
    }

    @GetMapping("/logs/service/{service}")
    public List<LogEntity> getLogsByService(@PathVariable String service) {
        return logRepository.findByService(service);
    }

    @GetMapping("/logs/service/{service}/level/{level}")
    public List<LogEntity> getLogsByServiceAndLevel(
        @PathVariable String service,
        @PathVariable String level
    ) {
        return logRepository.findByServiceAndLevel(service, level);
    }

    @GetMapping("/analytics/top-services")
    public List<Map.Entry<String, Integer>> getTopServices() {
        return logAnalyticsService.getTopServices();
    }

    @GetMapping("/analytics/errors/summary")
    public Map<String, Object> getAnalyticsSummary() {
        return logAnalyticsService.getErrorSummary();
    }

    @GetMapping("/metrics")
    public List<Map<String, Object>> getMetrics() {
        return logAnalyticsService.getLevelMetrics();
    }

    @GetMapping("/health")
    public Map<String, String> getHealth() {
        Map<String, String> healthMetric = new HashMap<>();
        healthMetric.put("status", "UP");
        healthMetric.put("database", "UP");
        healthMetric.put("kafka", "UP");

        return healthMetric;
    }

    @GetMapping("/analytics/anomalies")
    public List<Map<String, String>> getAnomalies() {
        return logAnalyticsService.getAnomalies();
    }
}
