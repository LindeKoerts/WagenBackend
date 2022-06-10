package com.wagengaragebackend.controller;

import com.wagengaragebackend.data.Customer;
import com.wagengaragebackend.dto.CustomerDto;
import com.wagengaragebackend.dto.CustomerInputDto;
import com.wagengaragebackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }




    @GetMapping("")
    public ResponseEntity<Object> searchCustomers() {
        List<Customer> customers = customerService.getCustomers();
        List<CustomerDto> customersDto = new ArrayList<>();
        for(Customer customer : customers ){
            CustomerDto customerDto = new CustomerDto();
            customerDto = customerDto.fromCustomer(customer);
            customersDto.add(customerDto); }
        return ResponseEntity.ok().body(customersDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable("id") long id) {
        Customer customer = customerService.getCustomerById(id);
        CustomerDto customerDto = new CustomerDto();
        customerDto = customerDto.fromCustomer(customer);
        return ResponseEntity.ok().body(customerDto);
    }


    @GetMapping ("/name")
    public ResponseEntity<Object> getByName(@RequestParam String name){
        Customer customer = customerService.getCustomerByName(name);
        CustomerDto customerDto = new CustomerDto();
        customerDto = customerDto.fromCustomer(customer);
        return ResponseEntity.ok().body(customerDto);
    }

    @PostMapping("")
    public ResponseEntity<Object> addCustomer(@RequestBody CustomerInputDto customerInputDto){
        Customer customer= CustomerInputDto.toCustomer(customerInputDto);

        long newId = customerService.addCustomer(customer);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable("id") long id, @RequestBody CustomerDto customerDto) {
        Customer customer= new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setTelephone(customerDto.getTelephone());
        customerService.updateCustomerById(id, customer);
        return ResponseEntity.noContent().build().ok("Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeCustomer(@PathVariable("id") long id) {
        customerService.removeCustomerById(id);
        return ResponseEntity.noContent().build().ok("Deleted");
    }







}