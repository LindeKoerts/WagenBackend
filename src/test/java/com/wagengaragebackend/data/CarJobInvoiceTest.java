package com.wagengaragebackend.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarJobInvoiceTest {

    private CarJobInvoice invoice;

    @BeforeEach
    void setUp(){

        List<String> operationDescriptions = new ArrayList<>();
        operationDescriptions.add("test1");
        operationDescriptions.add("test2");
        List<String> partDescriptions = new ArrayList<>();
        partDescriptions.add("part1");
        partDescriptions.add("part2");
        this.invoice = new CarJobInvoice("jansen", "test", operationDescriptions,
                BigDecimal.valueOf(50.00),partDescriptions,BigDecimal.valueOf(100.50), BigDecimal.valueOf(160.00));
    }

    @Test
    void testGetCustomerName(){
        String expect = "jansen";
        String name = this.invoice.getCustomerName();
        assertEquals(expect, name);
    }

    @Test
    void testGetRemarks(){
        String expect="test";
        String remarks = this.invoice.getRemarks();
        assertEquals(expect, remarks);
    }

    @Test
    void testGetOperationDescriptions(){
        List<String> expect = Arrays.asList("test1", "test2");
        List<String> descript = this.invoice.getOperationDescriptions();
        assertEquals(expect, descript);
    }

    @Test
    void testGetPartDescriptions(){
        List<String> expect = Arrays.asList("part1", "part2");
        List<String> descript = this.invoice.getPartDescriptions();
        assertEquals(expect, descript);
    }

    @Test
    void testGetOperationsCharge(){
        BigDecimal expect = new BigDecimal(50.00);
        BigDecimal charge = this.invoice.getOperationsCharge();
        assertEquals(expect, charge);
    }

    @Test
    void testGetPartsCharge(){
        BigDecimal expect = new BigDecimal(100.50);
        BigDecimal charge = this.invoice.getPartsCharge();
        assertEquals(expect, charge);
    }

    @Test
    void testGetTotalCharge(){
        BigDecimal expect = new BigDecimal(160.00);
        BigDecimal total = this.invoice.getTotalCharge();
        assertEquals(expect, total);
    }

    @Test
    void testSetPartDescriptions(){
        List<String> newdescr = Arrays.asList("part3", "part4");
        this.invoice.setPartDescriptions(newdescr);
        List<String> expect = Arrays.asList("part3", "part4");
        List<String> descript = this.invoice.getPartDescriptions();
        assertEquals(expect, descript);
    }

    @Test
    void testSetTotalCharge(){
        BigDecimal newtotal = new BigDecimal(300.25);
        BigDecimal expect = new BigDecimal(300.25);
        this.invoice.setTotalCharge(newtotal);
        BigDecimal total = this.invoice.getTotalCharge();
        assertEquals(expect, total );
    }
}

