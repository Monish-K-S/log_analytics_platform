package com.monish.processing.consumer;

import com.monish.processing.model.LogEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LogConsumer {

    @KafkaListener(topics = "logs.raw")
    public void consume(LogEvent logEvent) {
        System.out.print(logEvent);
    }
}
