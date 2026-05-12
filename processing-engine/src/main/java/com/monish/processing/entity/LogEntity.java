package com.monish.processing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;
    private String service;
    private String level;
    private String message;
    private String host;

    public LogEntity() {}

    public void setId(Long id) {
        this.id = id;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return this.id;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public String getService() {
        return this.service;
    }

    public String getHost() {
        return this.host;
    }

    public String getLevel() {
        return this.level;
    }

    public String getMessage() {
        return this.message;
    }
}
