package com.monish.producer.generator;

import com.monish.producer.model.LogEvent;
import com.monish.producer.service.KafkaProducerService;
import java.time.LocalDateTime;
import java.util.Random;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LogGenerator {

    private final KafkaProducerService kafkaProducerService;

    public LogGenerator(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Scheduled(fixedRate = 2000)
    public void generateLogs() {
        final String[] services = {
            "auth-service",
            "payment-service",
            "order-service",
            "notification-service",
        };

        final String[] levels = { "INFO", "WARN", "ERROR", "DEBUG" };

        final String[] messages = {
            "Request processed successfully",
            "Connection timeout occurred",
            "Database query execution failed",
            "Authentication completed successfully",
        };
        final String[] hosts = {
            "server-01",
            "server-02",
            "api-gateway-01",
            "worker-node-01",
        };

        Random random = new Random();
        int random_number_1 = random.nextInt(services.length);
        int random_number_2 = random.nextInt(levels.length);
        int random_number_3 = random.nextInt(messages.length);
        int random_number_4 = random.nextInt(hosts.length);

        LogEvent logEvent = new LogEvent();
        LocalDateTime now = LocalDateTime.now();
        logEvent.setService(services[random_number_1]);
        logEvent.setTimestamp(now);
        logEvent.setLevel(levels[random_number_2]);
        logEvent.setMessage(messages[random_number_3]);
        logEvent.setHost(hosts[random_number_4]);

        kafkaProducerService.sendLog(logEvent);
    }
}
