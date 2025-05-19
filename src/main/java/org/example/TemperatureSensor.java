package org.example;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "temperature_sensors")
public class TemperatureSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String room; // "гостиная", "ванная", "кухня" и т.д.
    private Double value; // температура в °C
    private LocalDateTime timestamp;
}
