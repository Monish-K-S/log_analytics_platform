package com.monish.processing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableKafka
@EnableScheduling
public class ProcessingEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessingEngineApplication.class, args);
    }
}
