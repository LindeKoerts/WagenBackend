package com.wagengaragebackend.controller;

import com.wagengaragebackend.data.Car;
import com.wagengaragebackend.dto.CarDto;
import com.wagengaragebackend.dto.CarInputDto;
import com.wagengaragebackend.data.Customer;
import com.wagengaragebackend.service.CarService;
import com.wagengaragebackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    private CarService carService;

    private CustomerService customerService;

    @Autowired
    public CarController(CarService carService,  CustomerService customerService){
        this.carService = carService;
        this.customerService = customerService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getCars() {
        List<Car> cars = carService.getCars();
        List<CarDto> carsDto = new ArrayList<>();
        for(Car car : cars){
            carsDto.add(CarDto.fromCar(car)); }
        return ResponseEntity.ok(carsDto);}

    @GetMapping ("/{id}")
    public ResponseEntity<Object> getCar(@PathVariable("id") long id){
        Car car = carService.getCarById(id);
        CarDto carDto = new CarDto();
        carDto = carDto.fromCar(car);
        return ResponseEntity.ok().body(carDto);
    }

    @PostMapping("")
    public ResponseEntity<Object> addCar(@RequestBody CarInputDto carInputDto) {

        Car car = CarInputDto.toCar(carInputDto);
        Customer customer = customerService.getCustomerById(carInputDto.getCustomerId());
        car.setCustomer(customer);
        long newId =carService.addCar(car);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> partialUpdate(@PathVariable("id") long id, @RequestBody CarInputDto dto) {
        Map<String, Object> fields = new HashMap<>();
        var stat =dto.getType();
        fields.put("type", stat);
        var stet = dto.getLicensePlate();
        fields.put("licensePlate", stet);
        var stot = dto.getCustomerId();
        fields.put("CustomerId", stot);
        carService.partialUpdateCar(id, fields);
        return ResponseEntity.noContent().build().ok("updated");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable("id") long id, @RequestBody CarInputDto carInputDto){
        Car car = CarInputDto.toCar(carInputDto);
        carService.updateCar(id, car);
        return ResponseEntity.noContent().build().ok("Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeCar (@PathVariable("id") long id){
        carService.removeCarById(id);
        return ResponseEntity.noContent().build().ok("Deleted");
    }
}
