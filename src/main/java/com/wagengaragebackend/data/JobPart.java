package com.wagengaragebackend.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="jobparts")
public class JobPart {


    @EmbeddedId
    private JobPartID ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("partId")
    @JoinColumn(name = "part_id")
    @JsonIgnore
    private Part part;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("carJobId")
    @JoinColumn(name = "carjob_id")
    @JsonIgnore
    private CarJob carJob;

    private BigDecimal quantity;


    public JobPart() {
    }

    public JobPart( CarJob carJob, BigDecimal quantity) {
        this.part = part;
        this.carJob = carJob;
        this.quantity = quantity;
    }


    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public CarJob getCarJob() {
        return carJob;
    }

    public void setCarJob(CarJob carJob) {
        this.carJob = carJob;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public JobPartID getID() {
        return ID;
    }

    public void setID(JobPartID ID) {
        this.ID = ID;
    }
}
