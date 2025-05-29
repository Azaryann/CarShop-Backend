package am.azaryan.car.dto;

import lombok.Data;

@Data
public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private double price;
    private String imageUrl;
    private Long purchaserId;
}


