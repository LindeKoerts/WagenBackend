package com.wagengaragebackend.data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class JobOperationID implements Serializable{

    @Column(name="operation_id")
    private Long operationId;

    @Column(name="carjob_id")
    private Long carJobId;


    public JobOperationID() {
    }

    public JobOperationID(Long operationId, Long carJobId) {
        this.operationId = operationId;
        this.carJobId = carJobId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobOperationID that = (JobOperationID) o;
        return operationId.equals(that.operationId) && carJobId.equals(that.carJobId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationId, carJobId);
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public Long getCarJobId() {
        return carJobId;
    }

    public void setCarJobId(Long carJobId) {
        this.carJobId = carJobId;
    }
}