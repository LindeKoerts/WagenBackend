package com.wagengaragebackend.controller;

import com.wagengaragebackend.data.JobOperation;
import com.wagengaragebackend.data.JobOperationID;
import com.wagengaragebackend.data.JobPart;
import com.wagengaragebackend.dto.JobOperationDto;
import com.wagengaragebackend.dto.JobPartDto;
import com.wagengaragebackend.service.JobOperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/joboperations")
public class JobOperationController {


    private JobOperationService jobOperationService;

    public JobOperationController(JobOperationService jobOperationService) {
        this.jobOperationService = jobOperationService;
    }

    @PostMapping("/{carjob_id}/{operation_id}")
    public ResponseEntity<Object> addJobOperation(@PathVariable("carjob_id")Long carJobId,
                                                  @PathVariable("operation_id")Long operationId) {
        JobOperationID ID = jobOperationService.addJobOperation(carJobId, operationId);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(location).body(location);
    }


    @GetMapping("/{carjob_id}/{operation_id}")
    public ResponseEntity<Object> getJobOperation(@PathVariable("carjob_id") Long carJobId,
                                                  @PathVariable("operation_id") Long operationId){
        JobOperation jobOperation = jobOperationService.getJobOperationById(carJobId, operationId);
        JobOperationDto Dto = new JobOperationDto();
        Dto = Dto.fromJobOperation(jobOperation);
        return ResponseEntity.ok().body(Dto);
    }


    @GetMapping("/{carjob_id}/operations")
    public ResponseEntity<Object> getJobOperationsByCarJobId (@PathVariable("carjob_id") Long carJobId){
        Collection<JobOperation> jobOperations = jobOperationService.getJobOperationsByCarJobId(carJobId);
        List<JobOperationDto> Dtos = new ArrayList<>();
        for(JobOperation jobOperation : jobOperations){
            JobOperationDto dto = JobOperationDto.fromJobOperation(jobOperation);
            Dtos.add(dto);
        }
        return ResponseEntity.ok().body(Dtos);
    }
    @GetMapping("/{operation_id}/jobs")
    public ResponseEntity<Object> getJobOperations(@PathVariable("operation_id") Long operationId){
        Collection<JobOperation> jobOperations = jobOperationService.getJobOperationsByOperationId(operationId);
        List<JobOperationDto> Dtos = new ArrayList<>();
        for(JobOperation jobOperation : jobOperations){
            JobOperationDto dto = JobOperationDto.fromJobOperation(jobOperation);
            Dtos.add(dto);
        }
        return ResponseEntity.ok().body(Dtos);
    }

    @DeleteMapping("/{carjob_id}/{operation_id}")
    public ResponseEntity<Object> removeJobOperation (@PathVariable("carjob_id") Long carJobId,
                                                      @PathVariable("operation_id") Long operationId) {
        jobOperationService.removeJobOperation(carJobId, operationId);
        return ResponseEntity.noContent().build().ok("Deleted");
    }

    @PutMapping("/{carjob_id}/{operation_id}")
    public ResponseEntity<Object> updateJobOperation(@PathVariable("carjob_id") Long carJobId,
                                                     @PathVariable("operation_id") Long operationId) {
        jobOperationService.updateJobOperation(carJobId, operationId);
        return ResponseEntity.noContent().build().ok("Updated");
    }
}
