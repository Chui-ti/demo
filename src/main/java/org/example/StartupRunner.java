package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private SensorService sensorService;

    @Override
    public void run(String... args) {
        sensorService.simulateAndSave();
    }
}
