//package org.example.controllers;
//
//import org.example.SensorService;
//import org.example.TemperatureSensor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/test")
//public class TestController {
//    @Autowired
//    private SensorService sensorService;
//
//    @GetMapping("/simulate")
//    public String simulate() {
//        sensorService.simulateAndSave();
//        return "Данные датчиков сохранены в БД!";
//    }
//
//    @GetMapping("/temperature")
//    public List<TemperatureSensor> getTemperature() {
//        return sensorService.getTemperatureRepo().findAll();
//    }
//}