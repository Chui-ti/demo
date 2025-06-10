package org.example;

import lombok.Getter;
import org.example.repository.ElectricitySensorRepository;
import org.example.repository.FloorHeatingRepository;
import org.example.repository.TemperatureSensorRepository;
import org.example.repository.WaterSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


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

    @Transactional
    public List<GenerationReport> simulateAndSave() {
        List<GenerationReport> reports = new ArrayList<>();

        // Температура
        List<TemperatureSensor> tempData = simulator.generateTemperatureDataForYear();
        temperatureRepo.saveAll(tempData);
        reports.add(new GenerationReport("Данные по температуре сохранены", tempData.size()));

        // Вода
        List<WaterSensor> waterData = simulator.generateYearlyWaterData();
        waterRepo.saveAll(waterData);
        reports.add(new GenerationReport("Данные по потреблению воды сохранены", waterData.size()));

        // Электричество
        List<ElectricitySensor> electricityData = simulator.generateElectricityDataForYear();
        electricityRepo.saveAll(electricityData);
        reports.add(new GenerationReport("Данные по электричеству сохранены", electricityData.size()));

        // Тёплые полы
        List<FloorHeating> floorData = simulator.generateFloorHeatingDataForYear();
        floorHeatingRepo.saveAll(floorData);
        reports.add(new GenerationReport("Данные по использованию теплых полов сохранены", floorData.size()));

        return reports;
    }

    // В вашем SensorService
//    @Transactional
//    public void simulateAndSave() {
//        // Температура
//       // temperatureRepo.save(simulator.generateTemperature("гостиная"));
//        //temperatureRepo.save(simulator.generateTemperature("ванная"));
//
//        List<TemperatureSensor> data = simulator.generateTemperatureDataForYear();
//        temperatureRepo.saveAll(data);
//        System.out.println("Данные за год сохранены: " + data.size() + " записей.");
//
//
//        // Вода
////        waterRepo.save(simulator.generateWater("ванная"));
////        waterRepo.save(simulator.generateWater("кухня"));
//
//
//        List<WaterSensor> waterData = simulator.generateYearlyWaterData();
//        waterRepo.saveAll(waterData);
//
////        // Электричество
////        electricityRepo.save(simulator.generateElectricity("чайник"));
////        electricityRepo.save(simulator.generateElectricity("стиральная машина"));
//
//        electricityRepo.saveAll(simulator.generateElectricityDataForYear());
//
////        // Теплые полы
////        floorHeatingRepo.save(simulator.generateFloorHeating("ванная"));
////        floorHeatingRepo.save(simulator.generateFloorHeating("балкон"));
//
//        List<FloorHeating> heatingData = simulator.generateFloorHeatingDataForYear();
//        floorHeatingRepo.saveAll(heatingData);
//
//    }

}