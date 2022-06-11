package com.wagengaragebackend.repository;

import com.wagengaragebackend.data.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {

}

