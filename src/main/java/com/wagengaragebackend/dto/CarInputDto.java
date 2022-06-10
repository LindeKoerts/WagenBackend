package com.wagengaragebackend.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wagengaragebackend.data.Car;

public class CarInputDto {

    private String licensePlate;

    private String type;

    private Long customerId;



    public static Car toCar(CarInputDto carInputDto){
        Car car = new Car();
        car.setLicensePlate(carInputDto.getLicensePlate());
        car.setType(carInputDto.getType());
        return car;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


}
