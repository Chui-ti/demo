package org.example.repository;

import org.example.ElectricitySensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectricitySensorRepository extends JpaRepository<ElectricitySensor, Long> {
    List<ElectricitySensor> findByDevice(String device);
}