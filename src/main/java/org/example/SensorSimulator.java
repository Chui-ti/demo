package org.example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class SensorSimulator {
    private final Random random = new Random();

    // Генерация данных температуры
//    public TemperatureSensor generateTemperature(String room) {
//        TemperatureSensor sensor = new TemperatureSensor();
//        sensor.setRoom(room);
//        sensor.setValue(18 + random.nextDouble() * 10); // 18-28°C
//        sensor.setTimestamp(LocalDateTime.now());
//        return sensor;
//    }

    public List<TemperatureSensor> generateTemperatureDataForYear() {
        String[] rooms = {"bedroom", "livingroom", "toilet", "corridor", "kitchen"};
        List<TemperatureSensor> sensorDataList = new ArrayList<>();

        LocalDate startDate = LocalDate.now().minusYears(1).withDayOfYear(1); // начало прошлого года
        LocalDate endDate = LocalDate.now();

        for (String room : rooms) {
            LocalDate date = startDate;

            while (!date.isAfter(endDate)) {
                for (int hour = 0; hour < 24; hour++) {
                    for (int minute = 0; minute < 60; minute += 30) {
                        LocalTime time = LocalTime.of(hour, minute);
                        LocalDateTime timestamp = LocalDateTime.of(date, time);

                        TemperatureSensor sensor = new TemperatureSensor();
                        sensor.setRoom(room);
                        sensor.setValue(18 + random.nextDouble() * 10); // от 18 до 28
                        sensor.setTimestamp(timestamp);

                        sensorDataList.add(sensor);
                    }
                }

                date = date.plusDays(1);
            }
        }

        return sensorDataList;
    }

//    // Генерация данных воды
//    public WaterSensor generateWater(String location) {
//        WaterSensor sensor = new WaterSensor();
//        sensor.setLocation(location);
//        sensor.setFlowRate(random.nextDouble() * 2); // 0-2 л/мин
//        sensor.setTotalConsumption(random.nextDouble() * 100); // 0-100 м³
//        sensor.setTimestamp(LocalDateTime.now());
//        return sensor;
//    }

    public List<WaterSensor> generateYearlyWaterData() {
        String[] locations = {"kitchen", "bathroom"};
        List<WaterSensor> sensorDataList = new ArrayList<>();

        LocalDateTime start = LocalDateTime.now().minusYears(1);
        LocalDateTime end = LocalDateTime.now();

        for (String location : locations) {
            double totalConsumption = 0.0;

            LocalDateTime current = start;
            while (current.isBefore(end)) {
                double flowRate;

                // Имитируем большее потребление утром и вечером
                int hour = current.getHour();
                if ((hour >= 6 && hour <= 9) || (hour >= 18 && hour <= 22)) {
                    flowRate = 0.5 + random.nextDouble() * 1.0; // 0.5 - 1.5 л/мин
                } else {
                    flowRate = random.nextDouble() * 0.3; // 0 - 0.3 л/мин
                }

                // Расход за час = flowRate * 60
                double hourlyConsumption = flowRate * 60 / 1000; // в м³
                totalConsumption += hourlyConsumption;

                WaterSensor sensor = new WaterSensor();
                sensor.setLocation(location);
                sensor.setFlowRate(flowRate);
                sensor.setTotalConsumption(Math.round(totalConsumption * 1000.0) / 1000.0); // округляем до 3 знаков
                sensor.setTimestamp(current);

                sensorDataList.add(sensor);
                current = current.plusHours(1);
            }
        }

        return sensorDataList;
    }


//    // Генерация данных электричества
//    public ElectricitySensor generateElectricity(String device) {
//        ElectricitySensor sensor = new ElectricitySensor();
//        sensor.setDevice(device);
//        sensor.setPower(100 + random.nextDouble() * 2000); // 100-2100 Вт
//        sensor.setTotalConsumption(random.nextDouble() * 50); // 0-50 кВт*ч
//        sensor.setTimestamp(LocalDateTime.now());
//        return sensor;
//    }
public List<ElectricitySensor> generateElectricityDataForYear() {
    List<ElectricitySensor> dataList = new ArrayList<>();

    Map<String, Double[]> powerRanges = new HashMap<>();
    powerRanges.put("чайник", new Double[]{1500.0, 2200.0});
    powerRanges.put("стиральная машина", new Double[]{400.0, 1000.0});
    powerRanges.put("электрическая плита", new Double[]{1000.0, 2000.0});
    powerRanges.put("системный блок", new Double[]{100.0, 300.0});
    powerRanges.put("монитор", new Double[]{30.0, 70.0});
    powerRanges.put("светильники в спальне", new Double[]{10.0, 40.0});
    powerRanges.put("светильники в гостиной", new Double[]{10.0, 40.0});
    powerRanges.put("телевизор", new Double[]{80.0, 200.0});
    powerRanges.put("потолочные лампы (bedroom)", new Double[]{60.0, 120.0});
    powerRanges.put("потолочные лампы (livingroom)", new Double[]{60.0, 120.0});
    powerRanges.put("микроволновка", new Double[]{800.0, 1500.0});
    powerRanges.put("зарядка ноутбука", new Double[]{30.0, 90.0});
    powerRanges.put("зарядка телефонов", new Double[]{5.0, 20.0});

    int year = LocalDate.now().getYear();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");

    for (Map.Entry<String, Double[]> entry : powerRanges.entrySet()) {
        String device = entry.getKey();
        Double minPower = entry.getValue()[0];
        Double maxPower = entry.getValue()[1];

        for (int month = 1; month <= 12; month++) {
            int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
            for (int day = 1; day <= daysInMonth; day++) {
                for (int hour = 0; hour < 24; hour++) {
                    // Зарядка телефонов и ноутбука ночью (0–6 ч)
                    if ((device.contains("зарядка") && hour >= 0 && hour <= 6) ||
                            (!device.contains("зарядка") && random.nextDouble() < 0.2)) { // 20% шанс активности в час
                        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hour, 0, 0);
                        double power = minPower + random.nextDouble() * (maxPower - minPower);
                        double consumption = power / 1000.0 * (1.0 / 6.0); // за 10 мин в кВт·ч

                        ElectricitySensor sensor = new ElectricitySensor();
                        sensor.setDevice(device);
                        sensor.setPower(power);
                        sensor.setTotalConsumption(consumption);
                        sensor.setTimestamp(timestamp);

                        dataList.add(sensor);
                    }
                }
            }
        }
    }

    return dataList;
}


//    // Генерация данных теплых полов
//    public FloorHeating generateFloorHeating(String room) {
//        FloorHeating sensor = new FloorHeating();
//        sensor.setRoom(room);
//        sensor.setIsOn(random.nextBoolean()); // случайное включение/выключение
//        sensor.setTimestamp(LocalDateTime.now());
//        return sensor;
//    }

    public FloorHeating generateFloorHeating(String room, LocalDateTime timestamp) {
        FloorHeating sensor = new FloorHeating();
        sensor.setRoom(room);
        sensor.setTimestamp(timestamp);

        boolean isOn = false;
        int hour = timestamp.getHour();
        int month = timestamp.getMonthValue();

        switch (room) {
            case "bathroom" -> {
                // Утром и вечером
                isOn = (hour >= 6 && hour < 9) || (hour >= 19 && hour < 23);
            }
            case "balcony" -> {
                // Только зимой днём
                boolean isWinter = month == 12 || month == 1 || month == 2;
                isOn = isWinter && (hour >= 10 && hour < 18);
            }
        }

        sensor.setIsOn(isOn);
        return sensor;
    }

    public List<FloorHeating> generateFloorHeatingDataForYear() {
        List<FloorHeating> dataList = new ArrayList<>();
        String[] rooms = {"bathroom", "balcony"};
        LocalDate startDate = LocalDate.now().minusYears(1).withDayOfYear(1);
        LocalDate endDate = startDate.plusYears(1);

        for (String room : rooms) {
            LocalDate currentDate = startDate;
            while (!currentDate.isEqual(endDate)) {
                for (int hour = 0; hour < 24; hour++) {
                    for (int minute = 0; minute < 60; minute += 30) {
                        LocalDateTime timestamp = currentDate.atTime(hour, minute);
                        FloorHeating data = generateFloorHeating(room, timestamp);
                        dataList.add(data);
                    }
                }
                currentDate = currentDate.plusDays(1);
            }
        }

        return dataList;
    }

}