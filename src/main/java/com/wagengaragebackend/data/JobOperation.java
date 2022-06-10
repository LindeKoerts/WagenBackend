package com.wagengaragebackend.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="joboperations")
public class JobOperation {

    @EmbeddedId
    private JobOperationID ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("operationId")
    @JoinColumn(name = "operation_id")
    @JsonIgnore
    private Operation operation;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("carJobId")
    @JoinColumn(name = "carjob_id")
    @JsonIgnore
    private CarJob carJob;



    public JobOperation() {
    }
    public JobOperation( Operation operation, CarJob carJob) {
        this.operation =  operation;
        this.carJob = carJob;

    }


    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public CarJob getCarJob() {
        return carJob;
    }

    public void setCarJob(CarJob carJob) {
        this.carJob = carJob;
    }


    public JobOperationID getID() {
        return ID;
    }

    public void setID(JobOperationID ID) {
        this.ID = ID;
    }
}
