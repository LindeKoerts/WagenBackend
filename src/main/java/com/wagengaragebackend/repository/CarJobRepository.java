package com.wagengaragebackend.repository;

import com.wagengaragebackend.data.CarJob;
import com.wagengaragebackend.data.CarJobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarJobRepository extends JpaRepository<CarJob, Long> {

    List<CarJob> findByStatus(CarJobStatus status);
    CarJob findByStatusAndCarLicensePlate(CarJobStatus status,String licensePlate);
    CarJob findByStatusAndCustomerNameAndCustomerEmail(CarJobStatus status, String name, String email);

    CarJob findByStatusAndCustomerNameAndCustomerTelephone(CarJobStatus status, String name, String telephone);
}
