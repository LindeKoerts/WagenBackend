package com.wagengaragebackend.repository;

import com.wagengaragebackend.data.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
