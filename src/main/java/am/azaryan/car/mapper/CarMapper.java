package am.azaryan.car.mapper;

import am.azaryan.car.dto.CarDto;
import am.azaryan.car.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public CarDto toDto(Car car) {
        CarDto dto = new CarDto();
        dto.setId(car.getId());
        dto.setBrand(car.getBrand());
        dto.setModel(car.getModel());
        dto.setYear(car.getYear());
        dto.setPrice(car.getPrice());
        dto.setImageUrl(car.getImageUrl());
        if (car.getPurchaser() != null) {
            dto.setPurchaserId(car.getPurchaser().getId());
        }
        return dto;
    }

    public Car toEntity(CarDto carDto) {
        Car car = new Car();
        car.setId(carDto.getId());
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setYear(carDto.getYear());
        car.setPrice(carDto.getPrice());
        car.setImageUrl(carDto.getImageUrl());
        return car;
    }
}

