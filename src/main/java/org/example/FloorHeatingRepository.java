package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorHeatingRepository extends JpaRepository<FloorHeating, Long> {
    List<FloorHeating> findByRoomAndIsOn(String room, Boolean isOn);
}