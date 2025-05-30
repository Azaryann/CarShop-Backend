package am.azaryan.car.controller;

import am.azaryan.car.dto.CarDto;
import am.azaryan.car.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CarDto> createCar(
            @RequestPart("car") CarDto carDto,
            @RequestPart(value = "image", required = false) MultipartFile file
    ) {
        String imagePath = null;

        if (file != null && !file.isEmpty()) {
            imagePath = carService.saveCarImage(file);
        }

        if (imagePath != null) {
            carDto.setImageUrl(imagePath);
        }

        CarDto savedCar = carService.createCar(carDto, file);

        return ResponseEntity.ok(savedCar);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CarDto> updateCar(
            @PathVariable Long id,
            @RequestPart("car") CarDto carDto,
            @RequestPart(value = "image", required = false) MultipartFile file
    ) {
        return ResponseEntity.ok(carService.updateCar(id, carDto, file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/upload-image")
    public ResponseEntity<String> uploadCarImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            String fileName = carService.uploadFile(id, file);
            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("File upload failed: " + ex.getMessage());
        }
    }
}
