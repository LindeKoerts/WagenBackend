package com.wagengaragebackend.controller;

import com.wagengaragebackend.data.Part;
import com.wagengaragebackend.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/parts")
public class PartController {

    private PartService partService;

    @Autowired
    public PartController(PartService partService){
        this.partService = partService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getParts() {
        return ResponseEntity.ok(partService.getParts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPart(@PathVariable("id") long id) {
        return ResponseEntity.ok(partService.getPartById(id));
    }

    @PostMapping("")
    public ResponseEntity<Object> addPart (@RequestBody Part part) {
        long newId = partService.addPart(part);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePart(@PathVariable("id") long id, @RequestBody Part part) {
        partService.updatePart(id, part);
        return ResponseEntity.noContent().build().ok("Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removePart(@PathVariable("id") long id) {
        partService.removePart(id);
        return ResponseEntity.noContent().build().ok("Deleted");
    }










}
