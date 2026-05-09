package com.monish.producer.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class LogEvent {

    private LocalDateTime timestamp;
    private String service;
    private String level;
    private String message;
    private String host;
}
