package org.example;

import weka.core.*;
import weka.clusterers.SimpleKMeans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TemperatureAnalyzer {

    @Autowired
    private TemperatureSensorRepository temperatureRepo;

    public void detectAnomalies() throws Exception {
        // 1. Загрузка данных из БД
        LocalDateTime cutoffTime = LocalDateTime.now().minusHours(24);
        List<TemperatureSensor> data = temperatureRepo.findLast24Hours(cutoffTime);

        // 2. Подготовка данных для Weka
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("temperature"));

        Instances dataset = new Instances("Temperature", attributes, data.size());

        for (TemperatureSensor sensor : data) {
            double[] values = new double[1];
            values[0] = sensor.getValue();
            dataset.add(new DenseInstance(1.0, values));
        }

        // 3. Кластеризация (K-Means)
        SimpleKMeans kmeans = new SimpleKMeans();
        kmeans.setNumClusters(3); // Норма, холодно, жарко
        kmeans.buildClusterer(dataset);

        // 4. Определение аномалий
        for (int i = 0; i < dataset.size(); i++) {
            int cluster = kmeans.clusterInstance(dataset.get(i));
            if (cluster == 2) { // Условно "жарко"
                System.out.println("АНОМАЛИЯ: " + data.get(i).getRoom() + " — " + data.get(i).getValue() + "°C");
            }
        }
    }
}