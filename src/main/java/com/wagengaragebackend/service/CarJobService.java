package com.wagengaragebackend.service;

import com.wagengaragebackend.data.CarJob;
import com.wagengaragebackend.data.CarJobStatus;
import com.wagengaragebackend.data.Customer;
import com.wagengaragebackend.exception.BadRequestException;
import com.wagengaragebackend.exception.RecordNotFoundException;
import com.wagengaragebackend.repository.CarJobRepository;
import com.wagengaragebackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CarJobService {

    private CarJobRepository carJobRepository;

    private CustomerRepository customerRepository;

    @Autowired
    public CarJobService(CarJobRepository carJobRepository, CustomerRepository customerRepository){
        this.carJobRepository = carJobRepository;
        this.customerRepository  = customerRepository;
    }

    public List<CarJob> getCarJobs(){
        return carJobRepository.findAll();
    }

    public CarJob getCarJobById(long id) {
        if (carJobRepository.existsById(id)) {
            return carJobRepository.findById(id).get();}
        else { throw new RecordNotFoundException("Unknown Id");}
    }

    public long addCarJob(CarJob carJob) {

        Customer customer = carJob.getCar().getCustomer();
        if(customer == carJob.getCustomer()){
            CarJob addedCarJob = carJobRepository.save(carJob);
            return addedCarJob.getId();}
        else{throw new BadRequestException("that's not your own car");}
    }

    public void updateCarJob(long id, CarJob carJob){
        if(carJobRepository.existsById(id)){
            CarJob existingCarJob = carJobRepository.findById(id).get();
            existingCarJob.setStatus(carJob.getStatus());
            existingCarJob.setRepairDate(carJob.getRepairDate());
            existingCarJob.setRemarks(carJob.getRemarks());
            carJobRepository.save(existingCarJob);
        }else { throw new RecordNotFoundException();}
    }

    public void partialUpdateCarJob(long id, Map<String, Object> fields){
        if(carJobRepository.existsById(id)){
            CarJob carJob = carJobRepository.findById(id).get();
            for (String key : fields.keySet()){
                switch(key.toLowerCase()){
                    case "status":
                        carJob.setStatus((CarJobStatus) fields.get(key) );
                        break;
                    case "repairdate" :                                        //if(fields.get(key)instanceof LocalDateTime){
                        carJob.setRepairDate((LocalDateTime) fields.get(key));
                        break;
                    case "remarks" : carJob.setRemarks((String) fields.get(key));
                        break;
                } }
            carJobRepository.save(carJob);
        }else{throw new RecordNotFoundException();}
    }

    public List<CarJob> getCarJobsByStatus(CarJobStatus status){
        return carJobRepository.findByStatus(status);
    }

    public void deleteCarJobById(long id){
        if(carJobRepository.existsById(id)){
            carJobRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException(); }
    }
}

