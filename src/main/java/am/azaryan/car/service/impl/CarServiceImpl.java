package am.azaryan.car.service.impl;

import am.azaryan.car.dto.CarDto;
import am.azaryan.car.exception.ResourceNotFoundException;
import am.azaryan.car.mapper.CarMapper;
import am.azaryan.car.model.Car;

import am.azaryan.car.model.Purchaser;
import am.azaryan.car.repository.CarRepository;
import am.azaryan.car.repository.PurchaserRepository;
import am.azaryan.car.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final PurchaserRepository purchaserRepository;
    private final CarMapper carMapper;

    private static final String UPLOAD_DIRECTORY = "F:/Java24/CarShop-Backend/uploads";

    public List<CarDto> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }

    public CarDto getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));
        return carMapper.toDto(car);
    }

    public CarDto createCar(CarDto carDto, MultipartFile file) {
        Car car = carMapper.toEntity(carDto);
        if (carDto.getPurchaserId() != null) {
            Purchaser purchaser = purchaserRepository.findById(carDto.getPurchaserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Purchaser not found with id: " + carDto.getPurchaserId()));
            car.setPurchaser(purchaser);
        }
        if (file != null && !file.isEmpty()) {
            String imagePath = saveCarImage(file);
            car.setImageUrl(imagePath);
        }

        Car savedCar = carRepository.save(car);
        return carMapper.toDto(savedCar);
    }

    public String saveCarImage(MultipartFile file) {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIRECTORY);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            Files.copy(file.getInputStream(), filePath);

            // Возвращаем относительный путь для доступа по HTTP
            return "/uploads/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении изображения", e);
        }
    }

    public CarDto updateCar(Long id, CarDto carDto) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setYear(carDto.getYear());
        car.setPrice(carDto.getPrice());
        car.setImageUrl(carDto.getImageUrl());
        if (carDto.getPurchaserId() != null) {
            Purchaser purchaser = purchaserRepository.findById(carDto.getPurchaserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Purchaser not found with id: " + carDto.getPurchaserId()));
            car.setPurchaser(purchaser);
        }
        Car updatedCar = carRepository.save(car);
        return carMapper.toDto(updatedCar);
    }

    public void deleteCar(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));
        carRepository.delete(car);
    }

    public String uploadFile(Long id, MultipartFile file) throws IOException {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with id: " + id));

        String UPLOAD_DIR = "uploads/";
        Path uploadPath = Path.of(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = id + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        car.setImageUrl(fileName);
        carRepository.save(car);

        return fileName;
    }
}
