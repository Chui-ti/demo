package org.example.controllers;

import org.example.GenerationReport;
import org.example.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/simulate")
public class SimulationController {

    @Autowired
    private SensorService sensorService;

    @GetMapping("/generate")
    public List<GenerationReport> generateAll() {
        return sensorService.simulateAndSave();
    }
}


