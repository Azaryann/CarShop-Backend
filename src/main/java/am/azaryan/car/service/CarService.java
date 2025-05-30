package am.azaryan.car.service;

import am.azaryan.car.dto.CarDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {

    List<CarDto> getAllCars();

    CarDto getCarById(Long id);

    CarDto createCar(CarDto carDto, MultipartFile file);

    String saveCarImage(MultipartFile file);

    CarDto updateCar(Long id, CarDto carDto, MultipartFile file);

    void deleteCar(Long id);

    String uploadFile(Long id, MultipartFile file) throws IOException;
}


