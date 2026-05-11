package com.monish.processing.consumer;

import com.monish.common.model.LogEvent;
import com.monish.processing.analytics.LogAnalyticsService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LogConsumer {

    private final LogAnalyticsService logAnalyticsService;

    public LogConsumer(LogAnalyticsService logAnalyticsService) {
        this.logAnalyticsService = logAnalyticsService;
    }

    @KafkaListener(topics = "logs.raw")
    public void consume(LogEvent logEvent) {
        logAnalyticsService.processLog(logEvent);
    }
}
