package com.wagengaragebackend.dto;

import com.wagengaragebackend.data.Customer;

import javax.validation.constraints.Email;

public class CustomerInputDto {


    private String name;

    @Email(message = "use a valid Email-adress")
    private String email;

    private String telephone;



    public CustomerInputDto() {
    }

    public CustomerInputDto(String name, String email, String telephone) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
    }



    public static Customer toCustomer(CustomerInputDto customerInputDto){
        Customer customer= new Customer();
        customer.setName(customerInputDto.getName());
        customer.setEmail(customerInputDto.getEmail());
        customer.setTelephone(customerInputDto.getTelephone());
        return customer;}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
