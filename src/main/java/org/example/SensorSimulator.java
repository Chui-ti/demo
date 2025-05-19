package org.example;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class SensorSimulator {
    private final Random random = new Random();

    // Генерация данных температуры
    public TemperatureSensor generateTemperature(String room) {
        TemperatureSensor sensor = new TemperatureSensor();
        sensor.setRoom(room);
        sensor.setValue(18 + random.nextDouble() * 10); // 18-28°C
        sensor.setTimestamp(LocalDateTime.now());
        return sensor;
    }

    // Генерация данных воды
    public WaterSensor generateWater(String location) {
        WaterSensor sensor = new WaterSensor();
        sensor.setLocation(location);
        sensor.setFlowRate(random.nextDouble() * 2); // 0-2 л/мин
        sensor.setTotalConsumption(random.nextDouble() * 100); // 0-100 м³
        sensor.setTimestamp(LocalDateTime.now());
        return sensor;
    }

    // Генерация данных электричества
    public ElectricitySensor generateElectricity(String device) {
        ElectricitySensor sensor = new ElectricitySensor();
        sensor.setDevice(device);
        sensor.setPower(100 + random.nextDouble() * 2000); // 100-2100 Вт
        sensor.setTotalConsumption(random.nextDouble() * 50); // 0-50 кВт*ч
        sensor.setTimestamp(LocalDateTime.now());
        return sensor;
    }

    // Генерация данных теплых полов
    public FloorHeating generateFloorHeating(String room) {
        FloorHeating sensor = new FloorHeating();
        sensor.setRoom(room);
        sensor.setIsOn(random.nextBoolean()); // случайное включение/выключение
        sensor.setTimestamp(LocalDateTime.now());
        return sensor;
    }
}