package com.wagengaragebackend.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String telephone;


    @OneToMany(mappedBy = "customer")
    private List<CarJob> carJob;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Car> car;



    public Customer() {
    }

    public Customer(String name,String email, String telephone) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<CarJob> getCarjob() {
        return carJob;
    }

    public void setCarjob(List<CarJob> carjob) {
        this.carJob = carJob;
    }


    public List<Car> getCar() {
        return car;
    }

    public void setCar(List<Car> car) {
        this.car = car;
    }

}
