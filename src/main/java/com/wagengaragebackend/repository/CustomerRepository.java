package com.wagengaragebackend.repository;

import com.wagengaragebackend.data.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByNameIgnoreCase(String name);
    Optional<Customer> findCustomerByEmail(String email);
    Customer findByNameAndTelephone(String customerName, String telephone);
}
