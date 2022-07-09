package com.wagengaragebackend.service;

import com.wagengaragebackend.data.Operation;
import com.wagengaragebackend.exception.RecordNotFoundException;
import com.wagengaragebackend.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationService {

    private OperationRepository operationRepository;

    @Autowired
    public OperationService(OperationRepository operationRepository){
        this.operationRepository = operationRepository;
    }

    public long addOperation(Operation operation){
        Operation addedOperation = operationRepository.save(operation);
        return addedOperation.getId();
    }
    public List<Operation> getOperations(){
        return operationRepository.findAll();
    }

    public Operation getOperationById(long id) {
        Optional<Operation> operation = operationRepository.findById(id);
        if (operation.isPresent()) {
            return operation.get();
        } else {
            throw new RecordNotFoundException();
        }
    }

    public void removeOperationById(long id) {
        operationRepository.deleteById(id);

    }

    public void updateOperation(long id, Operation updateOperation) {
        Optional<Operation> optionalOperation = operationRepository.findById(id);
        if (optionalOperation.isPresent()) {
            operationRepository.save(updateOperation);
        } else {
            throw new RecordNotFoundException();
        }
    }
}

