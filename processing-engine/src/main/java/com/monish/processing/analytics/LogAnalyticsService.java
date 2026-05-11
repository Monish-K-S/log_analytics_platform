package com.monish.processing.analytics;

import com.monish.common.model.LogEvent;
import java.util.HashMap;
import java.util.Map;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LogAnalyticsService {

    private final Map<String, Integer> levelCounts = new HashMap<>();
    private final Map<String, Integer> serviceCounts = new HashMap<>();

    public void processLog(LogEvent logEvent) {
        levelCounts.put(
            logEvent.getLevel(),
            levelCounts.getOrDefault(logEvent.getLevel(), 0) + 1
        );
        serviceCounts.put(
            logEvent.getService(),
            serviceCounts.getOrDefault(logEvent.getService(), 0) + 1
        );
    }

    @Scheduled(fixedRate = 10000)
    public void printAnalytics() {
        System.out.println("===== Analytics Snapshot =====");
        System.out.println(" Level Counts:   ");
        System.out.println(levelCounts);
        System.out.println(" Service Counts:   ");
        System.out.println(serviceCounts);
    }
}
