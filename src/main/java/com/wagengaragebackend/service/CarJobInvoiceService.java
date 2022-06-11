package com.wagengaragebackend.service;

import com.wagengaragebackend.data.*;
import com.wagengaragebackend.exception.BadRequestException;
import com.wagengaragebackend.exception.RecordNotFoundException;
import com.wagengaragebackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

@Service
public class CarJobInvoiceService {


    private CarJobRepository carJobRepository;

    private CustomerRepository customerRepository;

    private JobOperationRepository jobOperationRepository;

    private JobPartRepository jobPartRepository;

    private CarJobInvoiceRepository carJobInvoiceRepository;


    @Autowired
    public CarJobInvoiceService(CarJobRepository carJobRepository, CustomerRepository customerRepository,
                                JobOperationRepository jobOperationRepository, JobPartRepository jobPartRepository,
                                CarJobInvoiceRepository carJobInvoiceRepository) {
        this.carJobRepository = carJobRepository;
        this.customerRepository = customerRepository;
        this.jobOperationRepository = jobOperationRepository;
        this.jobPartRepository = jobPartRepository;
        this.carJobInvoiceRepository = carJobInvoiceRepository;
    }




    public List<CarJobInvoice> getInvoices(){
        List<CarJobInvoice> inv = carJobInvoiceRepository.findAll();
        return inv;
    }

    public CarJobInvoice getInvoiceById(Long id){
        if(carJobInvoiceRepository.existsById(id)){
            return carJobInvoiceRepository.findById(id).get();
        }else{throw new RecordNotFoundException();}
    }


    // Ophalen juiste carjob, daaruit verzamelen info, berekenen totaalbedragen incl. btw  en genereren/opslaan factuur.

    public Long addCarJobInvoice(Long carJobId, String name, String telephone, String email, String licensePlate) {

        CarJob carJob = getCarJobFromOptionalInput(carJobId, name, telephone,  email, licensePlate);


        CarJobInvoice carJobInvoice = new CarJobInvoice();
        carJobId = carJob.getId();

        boolean statusOk = StatusCheck(carJob);
        if(statusOk){

            carJobInvoice.setCustomerName(carJob.getCustomer().getName());
            carJobInvoice.setRemarks(carJob.getRemarks());



            List<JobOperation> jobOperations = jobOperationRepository.findAllByCarJobId(carJobId);
            List<String> operationDescriptions = getOperationsDescriptions(jobOperations);

            List<JobPart> jobParts = jobPartRepository.findAllByCarJobId(carJobId);
            List<String> partDescriptions = getPartDescriptions(jobParts);

            carJobInvoice.setOperationDescriptions(operationDescriptions);
            carJobInvoice.setPartDescriptions(partDescriptions);



            BigDecimal operationsCharge = calculateOperationsCharge(jobOperations);

            BigDecimal partsCharge = calculatePartsCharge(jobParts);

            BigDecimal totalCharge = partsCharge.add(operationsCharge);

            carJobInvoice.setOperationsCharge(operationsCharge);
            carJobInvoice.setPartsCharge(partsCharge);
            carJobInvoice.setTotalCharge(totalCharge);

            changeStatus(carJob);


            carJobInvoiceRepository.save(carJobInvoice);

        }else { throw new BadRequestException("check status of carjob"); }

        return carJobInvoice.getId();
    }



    // Ophalen van juiste carjob voor invoice aan hand van opgave aan balie van ofwel: carjobID, klantnaam+telfoonnr,
    // klantnaam+email, of kenteken.    wellicht onnodig ? (evt. makkelijk te verwijderen)

    public CarJob getCarJobFromOptionalInput(Long carJobId, String name, String telephone, String email, String licensePlate){
        CarJob carJob = new CarJob();

        if(carJobId != null){
            if(!carJobRepository.existsById(carJobId)){throw new RecordNotFoundException("unknown carJobId");}
            else{ carJob = carJobRepository.findById(carJobId).get(); }

        }else if ( name != null && email != null){
            carJob = getCarJobFromNameAndEmail(name, email);

        }else if (name != null && telephone != null) {
            carJob = getCarJobFromNameAndTelephone(name, telephone);

        }else if(licensePlate != null){
            carJob = getCarJobFromLicensePlate(licensePlate);

        }else {throw new BadRequestException("foutieve invoer");}
        return carJob; }

// omdat een customer of car meerdere carjobs kan hebben moet status ook worden meegenomen(uitgaande van max één te invoicen job per customer)

    public CarJob getCarJobFromLicensePlate(String licensePlate){
        CarJob job = carJobRepository.findByStatusAndCarLicensePlate(CarJobStatus.COMPLETED, licensePlate);
        CarJob jobB = carJobRepository.findByStatusAndCarLicensePlate(CarJobStatus.DONOTEXECUTE, licensePlate);
        CarJob jobC = new CarJob();
        if(job != null){
            jobC = job;
        }else if(jobB != null){
            jobC  = jobB;
        }else{ throw new BadRequestException();}
        return jobC;
    }

    public CarJob getCarJobFromNameAndTelephone(String name, String telephone) {
        CarJob job = carJobRepository.findByStatusAndCustomerNameAndCustomerTelephone(CarJobStatus.COMPLETED, name, telephone);
        CarJob jobB = carJobRepository.findByStatusAndCustomerNameAndCustomerTelephone(CarJobStatus.DONOTEXECUTE, name, telephone);
        CarJob jobC = new CarJob();
        if (job != null) {
            jobC = job;
        } else if (jobB != null) {
            jobC = jobB;
        }else{ throw new BadRequestException();}
        return jobC;
    }

    public CarJob getCarJobFromNameAndEmail(String name, String email) {
        CarJob job = carJobRepository.findByStatusAndCustomerNameAndCustomerEmail(CarJobStatus.COMPLETED, name, email);
        CarJob jobB = carJobRepository.findByStatusAndCustomerNameAndCustomerEmail(CarJobStatus.DONOTEXECUTE, name, email);
        CarJob jobC = new CarJob();
        if (job != null) {
            jobC = job;
        } else if (jobB != null) {
            jobC = jobB;
        }else{ throw new BadRequestException();}
        return jobC;
    }




    public boolean StatusCheck(CarJob carJob){
        CarJobStatus status = carJob.getStatus();
        if( (status == CarJobStatus.COMPLETED) || (status == CarJobStatus.DONOTEXECUTE)){
            return true;}  return false;
    }


    public List<String> getOperationsDescriptions(List<JobOperation> jobOperations){
        List<String> operationDescriptions = new ArrayList<String>();
        for(JobOperation jobOperation : jobOperations) {
            operationDescriptions.add(jobOperation.getOperation().getDescription());
        }
        return operationDescriptions;
    }

    public List<String> getPartDescriptions(List<JobPart> jobParts){
        List<String> partDescriptions = new ArrayList<String>();
        for(JobPart jobPart : jobParts) {
            partDescriptions.add(jobPart.getPart().getDescription());
        } return partDescriptions; }



    public BigDecimal calculateOperationsCharge(List<JobOperation> jobOperations){
        BigDecimal operationsCharge  = new BigDecimal("0");
        for(JobOperation jobOperation : jobOperations) {

            BigDecimal price = jobOperation.getOperation().getPrice();
            operationsCharge = operationsCharge.add(price);
        }
        BigDecimal operationsWithVAT = operationsCharge.multiply(new BigDecimal(1.21));
        return operationsWithVAT;
    }


    public BigDecimal calculatePartsCharge(List<JobPart> jobParts){
        BigDecimal partsCharge = new BigDecimal(0);
        for(JobPart jobPart : jobParts){

            BigDecimal price = (jobPart.getPart().getPrice());
            BigDecimal quantity = jobPart.getQuantity();
            BigDecimal charge = price.multiply(quantity);
            partsCharge = partsCharge.add(charge);
        }
        BigDecimal partsWithVAT = partsCharge.multiply(new BigDecimal(1.21)) ;
        return partsWithVAT;
    }

    public void changeStatus(CarJob carJob){
        carJob.setStatus(CarJobStatus.INVOICED);
    }


    public void removeCarJobInvoiceById(long id) {
        if(carJobInvoiceRepository.existsById(id)){
            carJobInvoiceRepository.deleteById(id);}else{
            throw new RecordNotFoundException(""); }
    }



}

