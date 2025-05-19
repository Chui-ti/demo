package org.example;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "floor_heating")
public class FloorHeating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String room; // "ванная", "балкон"
    private Boolean isOn;
    private LocalDateTime timestamp;
}