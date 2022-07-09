package com.wagengaragebackend.service;

import com.wagengaragebackend.WagenGarageBackendApplication;
import com.wagengaragebackend.data.CarJob;
import com.wagengaragebackend.data.CarJobStatus;
import com.wagengaragebackend.repository.CarJobRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
@ContextConfiguration(classes= {WagenGarageBackendApplication.class})
public class CarJobServiceTest {

    @Autowired
    private CarJobService carJobService;

    @MockBean
    private CarJobRepository carJobRepository;

    @Mock
    CarJob carJob;

    @Test
    public void testAddCarJob(){

        CarJob carJob = new CarJob();
        carJob.setRemarks("test");
        carJob.setStatus(CarJobStatus.COMPLETED);
        LocalDateTime date = LocalDateTime.of(2020, Month.APRIL,12,22,10);
        carJob.setRepairDate(date);
        carJob.setId(22L);

        Mockito
                .when(carJobRepository.save(carJob))
                .thenReturn(carJob);

        Long found = carJobService.addCarJob(carJob);
        Long expect = carJob.getId();

        assertEquals(expect, found);
    }

    @Test
    void testGetCarJobById() {

        CarJob carJob = new CarJob();
        this.carJob.setRemarks("test");
        carJob.setId(12L);
        Long Id = this.carJob.getId();

        Mockito
                .when(carJobRepository.existsById(Id))
                .thenReturn(true);
        Mockito
                .when(carJobRepository.findById(Id))
                .thenReturn(Optional.of(carJob));

        CarJob expexted = carJob;
        CarJob found = carJobService.getCarJobById(Id);
        assertEquals(expexted, found);
    }
}
