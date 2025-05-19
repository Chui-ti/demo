package org.example;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "water_sensors")
public class WaterSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location; // "ванная", "кухня"
    private Double flowRate; // л/мин
    private Double totalConsumption; // м³
    private LocalDateTime timestamp;
}