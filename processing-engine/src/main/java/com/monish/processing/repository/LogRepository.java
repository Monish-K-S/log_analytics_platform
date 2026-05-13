package com.monish.processing.repository;

import com.monish.processing.entity.LogEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntity, Long> {
    public List<LogEntity> findByLevel(String level);
}
