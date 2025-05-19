package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaterSensorRepository extends JpaRepository<WaterSensor, Long> {
    List<WaterSensor> findByLocation(String location);
}