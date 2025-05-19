package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectricitySensorRepository extends JpaRepository<ElectricitySensor, Long> {
    List<ElectricitySensor> findByDevice(String device);
}