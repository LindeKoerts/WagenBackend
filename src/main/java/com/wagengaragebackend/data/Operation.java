package com.wagengaragebackend.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="operations")
public class Operation {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    private String description;

    private BigDecimal price;

    @OneToMany(mappedBy = "operation", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<JobOperation> operations;

    public Operation() {
    }

    public Operation(String description, BigDecimal price) {
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<JobOperation> getOperations() {
        return operations;
    }

    public void setOperations(List<JobOperation> operations) {
        this.operations = operations;
    }
}
