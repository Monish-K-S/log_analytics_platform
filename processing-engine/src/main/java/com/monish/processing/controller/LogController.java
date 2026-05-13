package com.monish.processing.controller;

import com.monish.processing.entity.LogEntity;
import com.monish.processing.repository.LogRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    private final LogRepository logRepository;

    public LogController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @GetMapping("/logs")
    public List<LogEntity> getAllLogs() {
        return logRepository.findAll();
    }

    @GetMapping("logs/level/{level}")
    public List<LogEntity> getLogsByLevel(@PathVariable String level) {
        return logRepository.findByLevel(level);
    }
}
