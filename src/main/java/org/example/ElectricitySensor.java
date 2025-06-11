package org.example;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "electricity_sensors")
public class ElectricitySensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String device;
    private Double power; // Вт
    private Double totalConsumption; // кВт*ч
    private LocalDateTime timestamp;

    @ManyToOne
    private Room room;

}
