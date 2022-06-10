package com.wagengaragebackend.dto;

import com.wagengaragebackend.data.CarJobInvoice;

import java.math.BigDecimal;
import java.util.List;

public class InvoiceDto {

    private String customerName;

    private String remarks;

    private List<String> operationDescriptions;

    private BigDecimal operationsCharge;

    private List<String> partDescriptions;

    private BigDecimal partsCharge;

    private BigDecimal totalCharge;



    public InvoiceDto fromInvoice(CarJobInvoice invoice){
        InvoiceDto dto = new InvoiceDto();
        dto.setCustomerName(invoice.getCustomerName());
        dto.setRemarks(invoice.getRemarks());
        dto.setOperationDescriptions(invoice.getOperationDescriptions());
        dto.setOperationsCharge(invoice.getOperationsCharge());
        dto.setPartDescriptions(invoice.getPartDescriptions());
        dto.setPartsCharge(invoice.getPartsCharge());
        dto.setTotalCharge(invoice.getTotalCharge());
        return dto;

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<String> getOperationDescriptions() {
        return operationDescriptions;
    }

    public void setOperationDescriptions(List<String> operationDescriptions) {
        this.operationDescriptions = operationDescriptions;
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

    public void setPartsCharge(BigDecimal partsCharge) {
        this.partsCharge = partsCharge;
    }

    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(BigDecimal totalCharge) {
        this.totalCharge = totalCharge;
    }


}
