package com.monish.processing.analytics;
import com.monish.common.model.LogEvent;
import com.monish.processing.repository.LogRepository;
import com.monish.processing.service.LuceneIndexService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class LogAnalyticsServiceTest{
    private LogAnalyticsService logAnalyticsService;
    private LogRepository logRepository;
    private LuceneIndexService luceneIndexService;

    @BeforeEach
    void setup(){
        logRepository = mock(LogRepository.class);
        luceneIndexService = mock(LuceneIndexService.class);

        logAnalyticsService = new LogAnalyticsService(logRepository, luceneIndexService);

    }

    @Test
    void processLog_shouldUpdateInfoCount(){
        LogEvent logEvent = new LogEvent();
        logEvent.setLevel("INFO");
        logEvent.setService("payment-service");
        logEvent.setMessage("Payment processed");
        logEvent.setHost("localhost");
        logEvent.setTimestamp(LocalDateTime.now());
        logAnalyticsService.processLog(logEvent);

        assertEquals(1, logAnalyticsService.getLevelCounts().get("INFO"));

    }

    @Test
    public void getAnomalies_shoulddetectHighErrorCount(){
        for(int i = 0; i < 5; i++){
            LogEvent logEvent = new LogEvent();
            logEvent.setLevel("ERROR");
            logEvent.setService("payment-service");
            logEvent.setMessage("Payment processed");
            logEvent.setHost("localhost");
            logEvent.setTimestamp(LocalDateTime.now());
            logAnalyticsService.processLog(logEvent);

        }
        List<Map<String, String>> anomalies = logAnalyticsService.getAnomalies();
        assertEquals(1, anomalies.size());
        assertEquals("HIGH ERROR RATE", anomalies.get(0).get("type"));

    }

}
