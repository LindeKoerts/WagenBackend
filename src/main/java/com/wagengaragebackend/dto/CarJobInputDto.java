package com.wagengaragebackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.wagengaragebackend.data.CarJob;
import com.wagengaragebackend.data.CarJobStatus;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


public class CarJobInputDto {


    private CarJobStatus status;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime repairDate;

    @Max(value = 255, message = "remarks should not be greater than 250")
    private String remarks;

    private Long customerId;

    private Long carId;


    public CarJobInputDto() {
    }


    public CarJob toCarJob(CarJobInputDto Dto) {
        CarJob carJob = new CarJob();
        carJob.setStatus(Dto.getStatus());
        carJob.setRepairDate(Dto.getRepairDate());
        carJob.setRemarks(Dto.getRemarks());
        return carJob;
    }


    public Long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(LocalDateTime repairDate) {
        this.repairDate = repairDate;
    }

    public CarJobStatus getStatus() {
        return status;
    }

    public void setStatus(CarJobStatus status) {
        this.status = status;
    }
}
