package com.wagengaragebackend.repository;

import com.wagengaragebackend.data.JobOperation;
import com.wagengaragebackend.data.JobOperationID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobOperationRepository extends JpaRepository<JobOperation, JobOperationID> {

    List<JobOperation> findAllByCarJobId(Long carJobId);

    List<JobOperation> findAllByOperationId(Long operationId);
}

