package com.wagengaragebackend.repository;

import com.wagengaragebackend.data.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Boolean existsByLicensePlate(String licensePlate);

    Optional<Car> findByLicensePlate(String licensePlate);


}
