package com.monish.processing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableKafka
@EnableScheduling
@OpenAPIDefinition(
    info = @Info(
        title = "Distributed Log Analytics Platform",
        version = "1.0",
        description = "Real-time observability platform built with Spring Boot, Kafka, PostgreSQL, and streaming analytics.",
        contact = @Contact(name = "Monish K S", email = "k6mg5343@gmail.com")
    )
)
public class ProcessingEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessingEngineApplication.class, args);
    }
}
