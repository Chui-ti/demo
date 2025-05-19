package org.example;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class SensorService {
    @Getter
    @Autowired
    private TemperatureSensorRepository temperatureRepo;

    @Autowired
    private WaterSensorRepository waterRepo;

    @Autowired
    private ElectricitySensorRepository electricityRepo;

    @Autowired
    private FloorHeatingRepository floorHeatingRepo;

    @Autowired
    private SensorSimulator simulator;

    // В вашем SensorService
    @Transactional
    public void simulateAndSave() {
        // Температура
        temperatureRepo.save(simulator.generateTemperature("гостиная"));
        temperatureRepo.save(simulator.generateTemperature("ванная"));

        // Вода
        waterRepo.save(simulator.generateWater("ванная"));
        waterRepo.save(simulator.generateWater("кухня"));

        // Электричество
        electricityRepo.save(simulator.generateElectricity("чайник"));
        electricityRepo.save(simulator.generateElectricity("стиральная машина"));

        // Теплые полы
        floorHeatingRepo.save(simulator.generateFloorHeating("ванная"));
        floorHeatingRepo.save(simulator.generateFloorHeating("балкон"));
    }

}