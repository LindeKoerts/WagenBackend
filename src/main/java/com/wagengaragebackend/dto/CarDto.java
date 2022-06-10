package com.wagengaragebackend.dto;

import com.wagengaragebackend.data.Car;
import com.wagengaragebackend.data.CarJob;

import java.util.ArrayList;
import java.util.List;

public class CarDto {



    private Long id;

    private String licensePlate;

    private String type;

    private CustomerDto customer;

    private  List<CarJobShortDto> carJobs;


    public CarDto() {
    }


    public static CarDto fromCar(Car car){
        CarDto carDto= new CarDto();
        carDto.setId(car.getId());
        carDto.setLicensePlate(car.getLicensePlate());
        carDto.setType(car.getType());
        carDto.setCustomer(CustomerDto.fromCustomer(car.getCustomer()));
        List<CarJobShortDto> carJobDtos = new ArrayList<>();
        List<CarJob> Jobs = car.getCarJobs();
        for(CarJob carJob : Jobs){
            CarJobShortDto dto = new CarJobShortDto();
            dto = dto.fromCarJob(carJob);

            carJobDtos.add(dto);}

        carDto.setCarJobs(carJobDtos);

        return carDto;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<CarJobShortDto> getCarJobs() {
        return carJobs;
    }

    public void setCarJobs(List<CarJobShortDto> carJobs) {
        this.carJobs = carJobs;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }
}
