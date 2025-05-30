package am.azaryan.car.model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String model;

    private int year;
    private double price;

    private String imageUrl;

    @ManyToOne
    private Purchaser purchaser;
}

