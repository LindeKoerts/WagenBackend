package com.wagengaragebackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wagengaragebackend.data.CarJob;
import com.wagengaragebackend.data.CarJobStatus;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class CarJobDto {


    private Long id;

    @JsonSerialize
    private CarJobStatus status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonSerialize
    private LocalDateTime repairDate;

    @Size(max=255)
    private String remarks;

    private CustomerDto customer;

    @JsonIgnore
    private CarDto car;


    public CarJobDto() {
    }

    public CarJobDto(CarJobStatus status, LocalDateTime repairDate, String remarks, CustomerDto customer, CarDto car ) {
        this.status = status;
        this.repairDate = repairDate;
        this.remarks = remarks;
        this.customer = customer;
        this.car = car;
    }

    public static CarJobDto fromCarJob(CarJob carJob){
        CarJobDto carJobDto = new CarJobDto();
        carJobDto.setId(carJob.getId());
        carJobDto.setStatus(carJob.getStatus());
        carJobDto.setRepairDate(carJob.getRepairDate());
        carJobDto.setRemarks(carJob.getRemarks());
        CustomerDto Dto = CustomerDto.fromCustomer(carJob.getCustomer());
        carJobDto.setCustomer(Dto);
        CarDto Dta = CarDto.fromCar(carJob.getCar());
        carJobDto.setCar(Dta);
        return carJobDto;
    }


    public CarJobStatus getStatus() {
        return status;
    }

    public void setStatus(CarJobStatus status) {
        this.status = status;
    }

    public LocalDateTime getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(LocalDateTime repairDate) {
        this.repairDate = repairDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

}
