package com.wagengaragebackend.data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class JobPartID implements Serializable {

    @Column(name="carjob_id")
    private Long carJobId;

    @Column(name="part_id")
    private Long partId;

    public JobPartID() {
    }

    public JobPartID(Long carJobId, Long partId) {
        this.carJobId = carJobId;
        this.partId = partId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobPartID jobPartID = (JobPartID) o;
        return carJobId.equals(jobPartID.carJobId) && partId.equals(jobPartID.partId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carJobId, partId);
    }

    public Long getCarJobId() {
        return carJobId;
    }

    public void setCarJobId(Long carJobId) {
        this.carJobId = carJobId;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }
}
