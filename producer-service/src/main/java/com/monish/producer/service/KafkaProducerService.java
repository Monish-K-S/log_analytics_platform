package com.monish.producer.service;

import com.monish.common.model.LogEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, LogEvent> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, LogEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLog(LogEvent logEvent) {
        kafkaTemplate.send("logs.raw", logEvent.getService(), logEvent);
    }
}
