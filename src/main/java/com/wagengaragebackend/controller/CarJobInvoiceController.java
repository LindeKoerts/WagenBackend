package com.wagengaragebackend.controller;

import com.wagengaragebackend.data.CarJobInvoice;
import com.wagengaragebackend.dto.InvoiceDto;
import com.wagengaragebackend.dto.InvoiceInputDto;
import com.wagengaragebackend.service.CarJobInvoiceService;
import com.wagengaragebackend.service.CarJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/carjobinvoices")
public class CarJobInvoiceController {



    private CarJobInvoiceService carJobInvoiceService;

    private CarJobService carJobService;

    @Autowired
    public CarJobInvoiceController(CarJobInvoiceService carJobInvoiceService, CarJobService carJobService) {
        this.carJobInvoiceService = carJobInvoiceService;
        this.carJobService = carJobService;
    }



    @GetMapping("")
    public ResponseEntity<Object> getInvoices() {
        List<CarJobInvoice> carJobInvoices = carJobInvoiceService.getInvoices();
        List<InvoiceDto> Dtos = new ArrayList<>();
        for(CarJobInvoice invoice : carJobInvoices ){
            InvoiceDto dto = new InvoiceDto();
            dto = dto.fromInvoice(invoice);
            Dtos.add(dto);
        }
        return ResponseEntity.ok().body(Dtos);
    }

    @GetMapping("/{carjobinvoice_id}")
    public ResponseEntity<Object> getInvoiceById(@PathVariable("carjobinvoice_id") Long id ){
        CarJobInvoice invoice = carJobInvoiceService.getInvoiceById(id);
        InvoiceDto dto = new InvoiceDto();
        dto = dto.fromInvoice(invoice);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("")
    public ResponseEntity<Object> addInvoice(@RequestBody InvoiceInputDto invoiceInputDto){


        Long Id = carJobInvoiceService.addCarJobInvoice(invoiceInputDto.carJobId, invoiceInputDto.customerName,
                invoiceInputDto.telephone, invoiceInputDto.email, invoiceInputDto.licensePlate  );

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(Id).toUri();

        return  ResponseEntity.created(location).body(location);
    }


    @DeleteMapping("/{carjobinvoice_id}")
    public ResponseEntity<Object> removeInvoice(@PathVariable("carjobinvoice_id") long id) {
        carJobInvoiceService.removeCarJobInvoiceById(id);
        return ResponseEntity.noContent().build().ok("Deleted");
    }


}
