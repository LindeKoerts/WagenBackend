package com.wagengaragebackend.controller;

import com.wagengaragebackend.data.JobPart;
import com.wagengaragebackend.data.JobPartID;
import com.wagengaragebackend.dto.JobPartDto;
import com.wagengaragebackend.dto.JobPartInputDto;
import com.wagengaragebackend.service.JobPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/jobparts")
public class JobPartController {


    private JobPartService jobPartService;


    @Autowired
    public JobPartController(JobPartService jobPartService) {
        this.jobPartService = jobPartService;
    }



    @PostMapping("/{carjob_id}/{part_id}")
    public ResponseEntity<Object> addJobPart(@PathVariable("carjob_id")Long carJobId,
                                             @PathVariable("part_id")Long partId,
                                             @RequestBody JobPartInputDto dto) {

        BigDecimal quantity = dto.getQuantity();

        JobPartID ID = jobPartService.addJobPart(carJobId, partId, quantity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(location).body(location);
    }

    @GetMapping("/{carjob_id}/{part_id}")
    public ResponseEntity<Object> getJobPart(@PathVariable("carjob_id") Long carJobId,
                                             @PathVariable("part_id") Long partId){
        JobPart jobPart = jobPartService.getJobPartById(carJobId, partId);
        JobPartDto Dto =  new JobPartDto();
        Dto = Dto.fromJobPart(jobPart);
        return ResponseEntity.ok().body(Dto);
    }

    @GetMapping("/{part_id}/jobs")
    public ResponseEntity<Object> getJobPartsByPartId(@PathVariable("part_id") Long partId){
        Collection<JobPart> jobParts =  jobPartService.getJobPartsByPartId(partId);
        List<JobPartDto> Dtos= new ArrayList<>();
        for(JobPart jobPart : jobParts){
            JobPartDto dto= JobPartDto.fromJobPart(jobPart);
            Dtos.add(dto);
        }
        return ResponseEntity.ok().body(Dtos);
    }

    @GetMapping("/{carjob_id}/parts")
    public ResponseEntity<Object> getJobPartsByCarJobId (@PathVariable("carjob_id") Long carJobId){
        Collection<JobPart> jobParts =  jobPartService.getJobPartsByCarJobId(carJobId);
        List<JobPartDto> Dtos  = new ArrayList<>();
        for(JobPart jobPart : jobParts){
            JobPartDto dto= JobPartDto.fromJobPart(jobPart);
            Dtos.add(dto);
        }
        return ResponseEntity.ok().body(Dtos);
    }


    @DeleteMapping("/{carjob_id}/{part_id}")
    public ResponseEntity<Object> removeJobPart (@PathVariable("carjob_id") Long carJobId,
                                                 @PathVariable("part_id") Long partId) {
        jobPartService.removeJobPart(carJobId, partId);
        return ResponseEntity.noContent().build().ok("Deleted");
    }

    @PutMapping("/{carjob_id}/{part_id}")
    public ResponseEntity<Object> updateJobPart(@PathVariable("carjob_id") Long carJobId,
                                                @PathVariable("part_id") Long partId,
                                                @RequestBody JobPartInputDto dto) {
        BigDecimal quantity = dto.getQuantity();
        jobPartService.updateJobPart(carJobId, partId, quantity);
        return ResponseEntity.noContent().build().ok("Updated");
    }

}
