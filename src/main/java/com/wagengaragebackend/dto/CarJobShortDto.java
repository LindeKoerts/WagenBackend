package com.wagengaragebackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wagengaragebackend.data.CarJob;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class CarJobShortDto {

    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonSerialize
    private LocalDateTime repairDate;

    @Size(max=255)
    private String remarks;

    public CarJobShortDto() {
    }

    public CarJobShortDto(Long id, LocalDateTime repairDate, String remarks) {
        this.id = id;
        this.repairDate = repairDate;
        this.remarks = remarks;
    }

    public static CarJobShortDto fromCarJob(CarJob carJob){
        CarJobShortDto carJobShortDto = new CarJobShortDto();
        carJobShortDto.setId(carJob.getId());
        carJobShortDto.setRepairDate(carJob.getRepairDate());
        carJobShortDto.setRemarks(carJob.getRemarks());
        return carJobShortDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
