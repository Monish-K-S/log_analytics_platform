package com.monish.producer.runner;

import com.monish.producer.model.LogEvent;
import com.monish.producer.service.KafkaProducerService;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LogProducerRunner implements CommandLineRunner {

    private final KafkaProducerService kafkaProducerService;

    public LogProducerRunner(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public void run(String... args) throws Exception {
        LogEvent logEvent = new LogEvent();
        LocalDateTime now = LocalDateTime.now();
        logEvent.setService("auth-service");
        logEvent.setTimestamp(now);
        logEvent.setLevel("ERROR");
        logEvent.setMessage("authentication error");
        logEvent.setHost("server-1");

        kafkaProducerService.sendLog(logEvent);
    }
}
