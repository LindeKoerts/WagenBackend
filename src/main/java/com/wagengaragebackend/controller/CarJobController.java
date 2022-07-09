package com.wagengaragebackend.controller;

import com.wagengaragebackend.data.Car;
import com.wagengaragebackend.data.*;
import com.wagengaragebackend.data.CarJob;
import com.wagengaragebackend.data.Customer;
import com.wagengaragebackend.dto.CarJobDto;
import com.wagengaragebackend.dto.CarJobInputDto;

import com.wagengaragebackend.service.*;
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
@RequestMapping("/api/v1/carjobs")
public class CarJobController {

    private CarJobService carJobService;

    private CustomerService customerService;

    private CarService carService;

    private JobPartService jobPartService;

    private JobOperationService jobOperationService;


    @Autowired
    public CarJobController(CarJobService carJobService, CustomerService customerService, CarService carService, JobPartService jobPartService){
        this.carJobService =  carJobService;
        this.customerService= customerService;
        this.carService= carService;
        this.jobPartService = jobPartService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getCarJobs() {
        List<CarJob> carJobs = carJobService.getCarJobs();
        List<CarJobDto> carJobsDto = new ArrayList<>();
        for(CarJob carJob : carJobs){
            CarJobDto carJobDto = CarJobDto.fromCarJob(carJob);
            carJobsDto.add(carJobDto); }
        return ResponseEntity.ok().body(carJobsDto);}

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCarJobById(@PathVariable("id") long id) {
        CarJob carJob = carJobService.getCarJobById(id);
        CarJobDto dto = CarJobDto.fromCarJob(carJob);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/status")
    public ResponseEntity<Object> getByStatus(@RequestParam CarJobStatus status){
        List<CarJob> carJobs = carJobService.getCarJobsByStatus(status);
        List<CarJobDto> carJobsDto = new ArrayList<>();
        for(CarJob carJob : carJobs){
            CarJobDto carJobDto = new CarJobDto();
            carJobDto = CarJobDto.fromCarJob(carJob);
            carJobsDto.add(carJobDto); }
        return ResponseEntity.ok().body(carJobsDto);
    }

    @PostMapping("")
    public ResponseEntity<Object> addCarJob (@RequestBody CarJobInputDto Dto) {
        CarJob carJob = Dto.toCarJob(Dto);
        Customer customer = customerService.getCustomerById(Dto.getCustomerId());
        carJob.setCustomer(customer);
        Car car = carService.getCarById(Dto.getCarId() );
        carJob.setCar(car);
        long newId = carJobService.addCarJob(carJob);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCarJob(@PathVariable("id") long id, @RequestBody CarJobInputDto Dto) {
        CarJob carJob = Dto.toCarJob(Dto);
        carJobService.updateCarJob(id, carJob);
        return ResponseEntity.noContent().build().ok("Updated");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> partialUpdate(@PathVariable("id") long id, @RequestBody CarJobInputDto dto){
        Map<String, Object > fields = new HashMap<>();
        var stat=dto.getStatus();
        fields.put("status", stat);
        var stet =dto.getRepairDate();
        fields.put("repairDate", stet);
        var stot =dto.getRemarks();
        fields.put("remarks", stot);
        carJobService.partialUpdateCarJob(id, fields);
        return ResponseEntity.noContent().build().ok("Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeCarJob(@PathVariable("id") long id) {
        carJobService.deleteCarJobById(id);
        return ResponseEntity.noContent().build().ok("Deleted");
    }
}
