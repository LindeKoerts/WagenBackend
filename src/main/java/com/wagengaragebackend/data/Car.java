package com.wagengaragebackend.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String licensePlate;

    private String type;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "car")
    private List<CarJob> carJobs;

    public Car() {
    }

    public Car(String licensePlate, String type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CarJob> getCarJobs() {
        return carJobs;
    }

    public void setCarJobs(List<CarJob> carJobs) {
        this.carJobs = carJobs;
    }
}
