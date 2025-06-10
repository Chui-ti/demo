package org.example.repository;

import org.example.TemperatureSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TemperatureSensorRepository extends JpaRepository<TemperatureSensor, Long> {

    // Вариант 1: С параметром (рекомендуется)
    @Query("SELECT t FROM TemperatureSensor t WHERE t.timestamp >= :cutoffTime")
    List<TemperatureSensor> findByTimestampAfter(@Param("cutoffTime") LocalDateTime cutoffTime);

    // ИЛИ Вариант 2: Нативный запрос для PostgreSQL
    @Query(value = "SELECT * FROM temperature_sensors WHERE timestamp >= NOW() - INTERVAL '24 hours'",
            nativeQuery = true)
    List<TemperatureSensor> findLast24HoursNative();
}