package com.wagengaragebackend.repository;

import com.wagengaragebackend.data.CarJobInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarJobInvoiceRepository extends JpaRepository<CarJobInvoice, Long > {
}
