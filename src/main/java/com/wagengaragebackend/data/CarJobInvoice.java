package com.wagengaragebackend.data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "carjobinvoices")
public class CarJobInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String customerName;

    private String remarks;

    @ElementCollection
    private List<String> operationDescriptions;

    private BigDecimal operationsCharge;

    @ElementCollection
    private List<String> partDescriptions;

    private BigDecimal partsCharge;

    private BigDecimal totalCharge;

    public CarJobInvoice() {
    }

    public CarJobInvoice( String customerName, String remarks, List<String> operationDescriptions,
                          BigDecimal operationsCharge,List<String> partDescriptions,
                          BigDecimal partsCharge, BigDecimal totalCharge) {
        this.customerName = customerName;
        this.remarks = remarks;
        this.operationDescriptions = operationDescriptions;
        this.operationsCharge = operationsCharge;
        this.partDescriptions = partDescriptions;
        this.partsCharge = partsCharge;
        this.totalCharge = totalCharge;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<String> getOperationDescriptions() {
        return operationDescriptions;
    }

    public void setOperationDescriptions(List<String> operationDescriptions) {
        this.operationDescriptions = operationDescriptions;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BigDecimal getOperationsCharge() {
        return operationsCharge;
    }

    public void setOperationsCharge(BigDecimal operationsCharge) {
        this.operationsCharge = operationsCharge;
    }

    public List<String> getPartDescriptions() {
        return partDescriptions;
    }

    public void setPartDescriptions(List<String> partDescriptions) {
        this.partDescriptions = partDescriptions;
    }

    public BigDecimal getPartsCharge() {
        return partsCharge;
    }

    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(BigDecimal totalCharge) {
        this.totalCharge = totalCharge;
    }

    public void setPartsCharge(BigDecimal partsCharge) {
        this.partsCharge = partsCharge;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
