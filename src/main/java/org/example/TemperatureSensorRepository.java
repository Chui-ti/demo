package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TemperatureSensorRepository extends JpaRepository<TemperatureSensor, Long> {
    // Найти все записи за последние 24 часа
    @Query("SELECT t FROM TemperatureSensor t WHERE t.timestamp >= :cutoffTime")
    List<TemperatureSensor> findLast24Hours(LocalDateTime cutoffTime);

    // Или альтернативный вариант с вычислением времени в запросе:
    @Query("SELECT t FROM TemperatureSensor t WHERE t.timestamp >= CURRENT_TIMESTAMP - INTERVAL '24 hours'")
    List<TemperatureSensor> findLast24Hours();
}