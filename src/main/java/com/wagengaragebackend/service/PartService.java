package com.wagengaragebackend.service;

import com.wagengaragebackend.data.Part;
import com.wagengaragebackend.exception.RecordNotFoundException;
import com.wagengaragebackend.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartService {

    private PartRepository partRepository;

    @Autowired
    public PartService(PartRepository partRepository){
        this.partRepository = partRepository;
    }

    public List<Part> getParts(){
        return partRepository.findAll();
    }

    public Part getPartById(long id) {
        if (partRepository.existsById(id)) {
            return partRepository.findById(id).get();
        } else {
            throw new RecordNotFoundException();
        }
    }

    public long addPart(Part part){
        Part addedPart = partRepository.save(part);
        return addedPart.getId();
    }

    public void removePart(long id) {
        if(partRepository.existsById(id)){
            partRepository.deleteById(id);
        }else {
            throw new RecordNotFoundException(); }
    }

    public void updatePart(long id, Part updatePart) {
        Optional<Part> optionalPart = partRepository.findById(id);
        if (optionalPart.isPresent()) {
            partRepository.save(updatePart);
        } else {
            throw new RecordNotFoundException();
        }
    }
}
