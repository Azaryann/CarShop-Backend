package am.azaryan.car.repository;

import am.azaryan.car.model.Car;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
}
