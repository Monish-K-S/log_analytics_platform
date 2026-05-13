package com.monish.processing.repository;

import com.monish.processing.entity.LogEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntity, Long> {
    public List<LogEntity> findByLevel(String level);
    public List<LogEntity> findByService(String service);
    public List<LogEntity> findAllByOrderByTimestampDesc(Pageable pageable);
    public List<LogEntity> findByServiceAndLevel(String service, String level);
    public List<LogEntity> findByTimestampAfter(LocalDateTime timestamp);
}
