package com.wagengaragebackend.dto;

import com.wagengaragebackend.data.Car;
import com.wagengaragebackend.data.Customer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class CustomerDto {

    // @NotEmpty
    private Long id;
    @NotEmpty
    private String name;
    @Email
    private String email;

    private String telephone;

    private List<Car> car;




    public static CustomerDto fromCustomer(Customer customer){
        CustomerDto customerDto= new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setTelephone(customer.getTelephone());
        return customerDto;
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




}
