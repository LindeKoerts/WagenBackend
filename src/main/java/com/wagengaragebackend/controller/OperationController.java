package com.wagengaragebackend.controller;

import com.wagengaragebackend.data.Operation;
import com.wagengaragebackend.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/operations")
public class OperationController {


    private OperationService operationService;



    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }





    @GetMapping("")
    public ResponseEntity<Object> getOperation() {
        return ResponseEntity.ok(operationService.getOperations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOperation(@PathVariable("id") long id) {
        return ResponseEntity.ok(operationService.getOperationById(id));
    }

    @PostMapping("")
    public ResponseEntity<Object> addOperation(@RequestBody Operation operation){
        long newId = operationService.addOperation(operation);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOperation(@PathVariable("id") long id, @RequestBody Operation operation) {
        operationService.updateOperation(id, operation);
        return ResponseEntity.noContent().build().ok("Updated");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeOperation(@PathVariable("id") long id) {
        operationService.removeOperationById(id);
        return ResponseEntity.noContent().build().ok("Deleted");
    }

}
