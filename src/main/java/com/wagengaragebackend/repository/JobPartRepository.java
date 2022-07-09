package com.wagengaragebackend.repository;

import com.wagengaragebackend.data.JobPart;
import com.wagengaragebackend.data.JobPartID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPartRepository extends JpaRepository<JobPart, JobPartID> {

    List<JobPart> findAllByCarJobId(Long carJobId);
    List<JobPart> findAllByPartId(Long partId);
}
