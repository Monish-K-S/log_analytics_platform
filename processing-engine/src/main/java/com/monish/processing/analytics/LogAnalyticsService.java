package com.monish.processing.analytics;

import com.monish.common.model.LogEvent;
import com.monish.processing.entity.LogEntity;
import com.monish.processing.repository.LogRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LogAnalyticsService {

    private final Map<String, Integer> levelCounts = new HashMap<>();
    private final Map<String, Integer> serviceCounts = new HashMap<>();
    private static final int ERROR_THRESHOLD = 10;

    private final LogRepository logRepository;

    public LogAnalyticsService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void processLog(LogEvent logEvent) {
        levelCounts.put(
            logEvent.getLevel(),
            levelCounts.getOrDefault(logEvent.getLevel(), 0) + 1
        );
        serviceCounts.put(
            logEvent.getService(),
            serviceCounts.getOrDefault(logEvent.getService(), 0) + 1
        );

        LogEntity logEntity = new LogEntity();
        logEntity.setTimestamp(logEvent.getTimestamp());
        logEntity.setHost(logEvent.getHost());
        logEntity.setLevel(logEvent.getLevel());
        logEntity.setService(logEvent.getService());
        logEntity.setMessage(logEvent.getMessage());
        logRepository.save(logEntity);
    }

    @Scheduled(fixedRate = 10000)
    public void printAnalytics() {
        System.out.println("===== Analytics Snapshot =====");
        System.out.println(" Level Counts:   ");
        System.out.println(levelCounts);
        System.out.println(" Service Counts:   ");
        System.out.println(serviceCounts);
        int errorCount = levelCounts.getOrDefault("ERROR", 0);
        if (errorCount > ERROR_THRESHOLD) {
            System.out.println("ALERT: HIGH ERROR volume detected");
            System.out.println("Current Error Count: " + errorCount);
        }
    }
}
