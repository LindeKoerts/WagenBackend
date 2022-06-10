package com.wagengaragebackend.data;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "carjobs")
public class CarJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private CarJobStatus status;

    private LocalDateTime repairDate;

    private String remarks;

    @OneToMany(mappedBy = "carJob", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobOperation> operations;

    @OneToMany(mappedBy = "carJob", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<JobPart> parts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;


    public CarJob() {
    }

    public CarJob(CarJobStatus status, LocalDateTime repairDate, String remarks,
                  Customer customer,Car car) {
        this.status = status;
        this.repairDate = repairDate;
        this.remarks = remarks;
        this.customer = customer;
        this.car = car;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<JobPart> getParts() {
        return parts;
    }

    public void setParts(List<JobPart> parts) {
        this.parts = parts;
    }

    public List<JobOperation> getOperations() {
        return operations;
    }

    public void setOperations(List<JobOperation> operations) {
        this.operations = operations;
    }

}
