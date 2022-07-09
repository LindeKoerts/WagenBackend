package com.wagengaragebackend.service;

import com.wagengaragebackend.data.*;
import com.wagengaragebackend.repository.CarJobInvoiceRepository;
import com.wagengaragebackend.repository.CarJobRepository;
import com.wagengaragebackend.repository.JobOperationRepository;
import com.wagengaragebackend.repository.JobPartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CarJobInvoiceServiceTest {

    @Mock
    CarJobRepository carJobRepository;

    @Mock
    CarJobInvoiceRepository carJobInvoiceRepository;

    @Mock
    JobOperationRepository jobOperationRepository;

    @Mock
    JobPartRepository jobPartRepository;

    @InjectMocks
    CarJobInvoiceService carJobInvoiceService;

    @BeforeEach
    void setup() {
        CarJobInvoiceService carJobInvoiceService;
    }

    @Captor
    ArgumentCaptor<CarJobInvoice> carJobInvoiceCaptor;

    @Test
    public void testGetCarJobInvoices(){

        //ARANGE
        CarJobInvoice carJobInvoice = new CarJobInvoice();
        CarJobInvoice carJobInvoice2 = new CarJobInvoice();

        List<CarJobInvoice> invoices = new ArrayList<>();
        invoices.add(carJobInvoice);
        invoices.add(carJobInvoice2);

        Mockito
                .doReturn(invoices).when(carJobInvoiceRepository).findAll();

        //ACT/ASSERT
        assertEquals(2, carJobInvoiceService.getInvoices().size());
        assertEquals(carJobInvoice2,carJobInvoiceService.getInvoices().get(1));

    }

    @Test
    public void testGetInvoiceById(){
        CarJobInvoice carJobInvoice = new CarJobInvoice();
        carJobInvoice.setId(1L);

        Mockito
                .when(carJobInvoiceRepository.existsById(1L))
                .thenReturn(true);
        Mockito
                .doReturn(Optional.of(carJobInvoice)).when(carJobInvoiceRepository).findById(1L);

        assertEquals(carJobInvoice, carJobInvoiceService.getInvoiceById(1L));
    }

    @Test
    public void testAddCarJobInvoiceShouldReturnAnInvoice(){

        //ARANGE
        CarJob job = new CarJob();
        Long carJobId = 12L;
        job.setId(carJobId);
        job.setStatus(CarJobStatus.COMPLETED);

        Operation operation = new Operation();
        operation.setDescription("testOperation");
        operation.setPrice(BigDecimal.valueOf(50.00));

        JobOperation jobOperation = new JobOperation();
        jobOperation.setOperation(operation);
        jobOperation.setCarJob(job);
        List<JobOperation> jobOperations = new ArrayList<>();
        jobOperations.add(jobOperation);

        Part part = new Part();
        part.setDescription("testPart1");
        part.setPrice(BigDecimal.valueOf(100.00));

        Part part2 = new Part();
        part2.setPrice(BigDecimal.valueOf(25.50));
        part2.setDescription("testPart2");

        Customer customer = new Customer();
        customer.setName("jansen");
        customer.setEmail("jans@mail");
        customer.setTelephone("1234");
        customer.setId(15L);

        Car car = new Car();
        car.setType("auto");
        car.setLicensePlate("12AB34");
        car.setCustomer(customer);

        job.setCustomer(customer);
        job.setCar(car);

        JobPart jobPart = new JobPart();
        jobPart.setCarJob(job);
        jobPart.setPart(part);
        jobPart.setQuantity(BigDecimal.valueOf(1));

        JobPart jobPart2 =new JobPart();
        jobPart2.setCarJob(job);
        jobPart2.setPart(part2);
        jobPart2.setQuantity(BigDecimal.valueOf(1));

        List<JobPart> jobParts = new ArrayList<>();
        jobParts.add(jobPart);
        jobParts.add(jobPart2);

        Mockito
                .doReturn(job).when(carJobRepository).findByStatusAndCustomerNameAndCustomerEmail(CarJobStatus.COMPLETED,"jansen","jans@mail" );
        Mockito
                .doReturn(jobOperations).when(jobOperationRepository).findAllByCarJobId(carJobId);

        Mockito
                .doReturn(jobParts).when(jobPartRepository).findAllByCarJobId(carJobId);
        //Mockito
        //  .doReturn(carJobInvoice).when(carJobInvoiceRepository).save(carJobInvoice);

        //ACT
        carJobInvoiceService.addCarJobInvoice(null, "jansen", null, "jans@mail", null);

        Mockito
                .verify(carJobInvoiceRepository).save(carJobInvoiceCaptor.capture());

        CarJobInvoice carJobInvoice1 = carJobInvoiceCaptor.getValue();

        BigDecimal expect = BigDecimal.valueOf((100 + 25.50 )*1.21);
        BigDecimal  var =  carJobInvoice1.getPartsCharge();                    //hier is gekozen voor afronding, bij hogere
        BigDecimal found = var.setScale(3, RoundingMode.HALF_EVEN);    // gewenste nauwkeurigheid de exacte berekening navolgen
        //ASSERT
        assertEquals("jansen", carJobInvoice1.getCustomerName() );
        assertEquals(expect, found);
        assertEquals("testOperation", carJobInvoice1.getOperationDescriptions().get(0));
    }

    @Test
    public void givenCarJobWithStatusCOMPLETEDOrDONOTEXECUTEShouldReturnTrue(){

        CarJob job = new CarJob();
        job.setStatus(CarJobStatus.DONOTEXECUTE);

        boolean statusOk = carJobInvoiceService.StatusCheck(job);

        assertTrue(statusOk);
    }

    @Test
    public void testGetOperationDescriptions(){

        CarJob job = new CarJob();
        job.setId(12L);

        Operation operation = new Operation();
        operation.setDescription("testOperation1");

        Operation operationB = new Operation();
        operationB.setDescription("testOperation2");

        JobOperation jobOperation = new JobOperation();
        jobOperation.setOperation(operation);
        jobOperation.setCarJob(job);

        JobOperation jobOperationB = new JobOperation();
        jobOperationB.setOperation(operationB);

        List<JobOperation> jobOperations = new ArrayList<>();
        jobOperations.add(jobOperation);
        jobOperations.add(jobOperationB);

        assertEquals(2, carJobInvoiceService.getOperationsDescriptions(jobOperations).size());
        assertEquals("testOperation2", carJobInvoiceService.getOperationsDescriptions(jobOperations).get(1));
    }

    @Test
    public void testGetPartDescriptions(){
        CarJob job = new CarJob();
        job.setId(12L);

        Part part = new Part();
        part.setDescription("testPart1");

        JobPart jobPart = new JobPart();
        jobPart.setPart(part);

        List<JobPart> jobParts = new ArrayList<>();
        jobParts.add(jobPart);

        assertEquals("testPart1", carJobInvoiceService.getPartDescriptions(jobParts).get(0));
    }
    @Test
    public void testCalculateOperationsCharge(){
        CarJob job = new CarJob();

        Operation operation = new Operation();
        operation.setPrice(BigDecimal.valueOf(50.50));

        JobOperation jobOperation = new JobOperation();
        jobOperation.setOperation(operation);

        List<JobOperation> jobOperations = new ArrayList<>();
        jobOperations.add(jobOperation);

        BigDecimal x =  new BigDecimal(50.50 );
        BigDecimal y = new BigDecimal(1.21);    // incl 21% VAT
        BigDecimal expect = y.multiply(x);

        BigDecimal found =     carJobInvoiceService.calculateOperationsCharge(jobOperations);
        assertEquals(expect, found);
    }

    @Test
    public void testCalculatePartsCharge(){

        Part part = new Part();
        part.setPrice(BigDecimal.valueOf(50.00));

        Part part2 = new Part();
        part2.setPrice(BigDecimal.valueOf(25.50));

        JobPart jobPart = new JobPart();
        jobPart.setPart(part);
        jobPart.setQuantity(BigDecimal.valueOf(2));

        JobPart jobPart2 =new JobPart();
        jobPart2.setPart(part2);
        jobPart2.setQuantity(BigDecimal.valueOf(2));

        List<JobPart> jobParts = new ArrayList<>();
        jobParts.add(jobPart);
        jobParts.add(jobPart2);

        BigDecimal expected = BigDecimal.valueOf(2*50.00 + 2*25.50).multiply(BigDecimal.valueOf(1.21));
        BigDecimal var = carJobInvoiceService.calculatePartsCharge(jobParts);

        BigDecimal found = var.setScale(3, RoundingMode.HALF_EVEN);   //hier is gekozen voor afronding, bij hogere
        assertEquals(expected, found );                                      // gewenste nauwkeurigheid  de exacte berekening navolgen
    }                                                                          //met new Bigdecimal variabelen
    @Test
    public void testChangeStatus(){
        CarJob carJob = new CarJob();

        carJobInvoiceService.changeStatus(carJob);

        assertEquals(CarJobStatus.INVOICED, carJob.getStatus());
    }

    @Test
    public void testGetCarJobFromOptionalInputCarJobId(){
        CarJob carJob = new CarJob();
        carJob.setId(1L);

        Mockito
                .when(carJobRepository.existsById(1L))
                .thenReturn(true);
        Mockito
                .doReturn(Optional.of(carJob)).when(carJobRepository).findById(1L);


        CarJob found = carJobInvoiceService.getCarJobFromOptionalInput(  1L, "jansen", null, "null",null);

        assertEquals(carJob ,found);
    }

    @Test
    public void testGetCarJobFromOptionalInputNameAndEmail(){

        CarJob carJob = new CarJob();
        Customer customer = new Customer();
        customer.setName("jansen");
        customer.setEmail("jansen@mail");
        carJob.setCustomer(customer);

        Mockito
                .doReturn(carJob).when(carJobRepository).findByStatusAndCustomerNameAndCustomerEmail(CarJobStatus.COMPLETED,"jansen","jansen@mail" );

        CarJob found = carJobInvoiceService.getCarJobFromOptionalInput(  null, "jansen", null, "jansen@mail",null);

        assertEquals(carJob ,found);
    }

    @Test
    public void testGetCarJobFromOptionalInputNameAndTelephone(){

        CarJob carJob = new CarJob();
        Customer customer = new Customer();
        customer.setName("jansen");
        customer.setTelephone("1234");
        carJob.setCustomer(customer);

        Mockito
                .doReturn(carJob).when(carJobRepository).findByStatusAndCustomerNameAndCustomerTelephone(CarJobStatus.COMPLETED, "jansen", "1234" );

        CarJob found = carJobInvoiceService.getCarJobFromOptionalInput(  null, "jansen", "1234", null,null);

        assertEquals(carJob ,found);
    }

    @Test
    public void testGetCarJobFromOptionalInputLicensePlate(){

        CarJob carJob = new CarJob();
        Car car = new Car();
        car.setLicensePlate("123AS45");
        carJob.setCar(car);
        carJob.setStatus(CarJobStatus.DONOTEXECUTE);

        Mockito
                .doReturn(carJob).when(carJobRepository).findByStatusAndCarLicensePlate( CarJobStatus.COMPLETED,"123AS45" );

        CarJob found = carJobInvoiceService.getCarJobFromOptionalInput(  null, null, null, null,"123AS45");

        assertEquals(carJob ,found);
    }

    @Test
    public void testGetCarJobFromOptionalInputWhenTwoOptions(){

        CarJob carJob = new CarJob();
        Car car = new Car();
        car.setLicensePlate("123AS45");
        carJob.setCar(car);
        carJob.setId(1L);

        Mockito
                .when(carJobRepository.existsById(1L))
                .thenReturn(true);
        Mockito
                .when(carJobRepository.findById(1L))
                .thenReturn(Optional.of(carJob));

        CarJob found = carJobInvoiceService.getCarJobFromOptionalInput(  1L, null, null, null,"123AS45");

        assertEquals(carJob ,found);
    }

    @Test
    public void testGetCarjobFromNameAndTelephone(){
        CarJob carJob = new CarJob();
        Customer customer = new Customer();
        customer.setName("jansen");
        customer.setTelephone("1234");
        carJob.setCustomer(customer);

        Mockito
                .doReturn(carJob).when(carJobRepository).findByStatusAndCustomerNameAndCustomerTelephone(CarJobStatus.COMPLETED, "jansen", "1234" );

        CarJob found = carJobInvoiceService.getCarJobFromNameAndTelephone("jansen", "1234");

        assertEquals(carJob, found);
    }

    @Test
    public void testRemoveCarJobInvoiceById() {
        CarJobInvoice carJobInvoice = new CarJobInvoice();
        carJobInvoice.setId(1L);

        Mockito
                .when(carJobInvoiceRepository.existsById(1L))
                .thenReturn(true);

        carJobInvoiceService.removeCarJobInvoiceById(1L);
        Mockito.verify(carJobInvoiceRepository, Mockito.times(1)).deleteById(1L);

    }
}
