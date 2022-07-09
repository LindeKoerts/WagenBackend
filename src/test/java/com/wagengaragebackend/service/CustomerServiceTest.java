package com.wagengaragebackend.service;

import com.wagengaragebackend.WagenGarageBackendApplication;
import com.wagengaragebackend.data.Customer;
import com.wagengaragebackend.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
@ContextConfiguration(classes= {WagenGarageBackendApplication.class})
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @MockBean
    CustomerRepository customerRepository;

    @Mock
    Customer customer;

    @Test
    public void testAddCustomer(){

        Customer customer = new Customer();
        customer.setName("jansen");
        customer.setEmail("jansen@mail");
        customer.setTelephone("1234");
        customer.setId(12L);


        Mockito
                .when(customerRepository.save(customer))
                .thenReturn(customer);

        Long expect = customer.getId();
        Long found = customerService.addCustomer(customer);

        assertEquals(expect, found);
    }

    @Test
    public void testGetCustomerById(){

        Customer customer = new Customer();
        customer.setName("jansen");
        customer.setEmail("jansen@mail");
        customer.setTelephone("1234");
        customer.setId(1L);

        Mockito
                .when(customerRepository.existsById(1L))
                .thenReturn(true);
        Mockito
                .doReturn(Optional.of(customer)).when(customerRepository).findById(1L);

        assertThat(customerService.getCustomerById(1L)).isEqualTo(customer);
    }
}

