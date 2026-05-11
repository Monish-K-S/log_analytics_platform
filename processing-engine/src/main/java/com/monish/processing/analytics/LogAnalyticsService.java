package com.monish.processing.analytics;

import com.monish.common.model.LogEvent;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class LogAnalyticsService {

    private final Map<String, Integer> levelCounts = new HashMap<>();

    public void processLog(LogEvent logEvent) {
        levelCounts.put(
            logEvent.getLevel(),
            levelCounts.getOrDefault(logEvent.getLevel(), 0) + 1
        );
        System.out.println(levelCounts);
    }
}
