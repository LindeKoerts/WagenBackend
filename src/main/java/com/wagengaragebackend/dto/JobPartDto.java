package com.wagengaragebackend.dto;

import com.wagengaragebackend.data.JobPart;
import com.wagengaragebackend.data.JobPartID;
import com.wagengaragebackend.data.Part;
import com.wagengaragebackend.data.CarJob;

import java.math.BigDecimal;

public class JobPartDto {

    private JobPartID ID;

    private String description;

    private BigDecimal quantity;


    public static JobPartDto fromJobPart(JobPart jobPart){
        JobPartDto Dto = new JobPartDto();
        Dto.setID(jobPart.getID());
        Dto.setDescription(jobPart.getPart().getDescription());
        Dto.setQuantity(jobPart.getQuantity());
        return Dto;
    }


    public JobPartID getID() {
        return ID;
    }

    public void setID(JobPartID ID) {
        this.ID = ID;
    }


    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
