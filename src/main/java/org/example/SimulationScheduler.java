//package org.example;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SimulationScheduler {
//    @Autowired
//    private SensorService sensorService;
//
//    @Scheduled(fixedRate = 300_000) // 5 минут
//    public void simulateSensors() {
//        sensorService.simulateAndSave();
//        System.out.println("Данные датчиков сохранены!");
//    }
//}