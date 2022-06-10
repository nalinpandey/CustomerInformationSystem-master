package com.Customer.information.system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.Customer.information.system.model.Customer;
import com.Customer.information.system.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author ragcrix
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class CustomerRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService CustomerService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Customer ragcrix;
    private Customer yigit;

    private final Long ragcrixCustomerNumber = 23L;
    private final Long yigitCustomerNumber = 91L;

    @Before
    public void setup() {
        ragcrix = new Customer();
        ragcrix.setId("aBc123");
        ragcrix.setName("ragcrix");
        ragcrix.setEmail("ragcrix@rg.com");
        ragcrix.setCustomerNumber(ragcrixCustomerNumber);
        ragcrix.setCourseList(Arrays.asList("Math", "Science"));
        ragcrix.setGpa(3.37f);

        yigit = new Customer();
        yigit.setId("dEf345");
        yigit.setName("yigit");
        yigit.setEmail("yigit@ygt.com");
        yigit.setCustomerNumber(yigitCustomerNumber);
        yigit.setCourseList(Arrays.asList("Turkish", "German"));
        yigit.setGpa(3.58f);
    }

    @Test
    public void givenCustomers_whenGetAllCustomers_thenReturnJsonArray() throws Exception {
        given(CustomerService.findAll()).willReturn(Arrays.asList(ragcrix));

        mvc.perform(get("/Customers/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(ragcrix.getName())));
    }

    @Test
    public void givenCustomer_whenFindByCustomerNumber_thenReturnJsonArray() throws Exception {
        given(CustomerService.findByCustomerNumber(ragcrixCustomerNumber)).willReturn(ragcrix);

        mvc.perform(get("/Customers/byCustomerNumber/{CustomerNumber}", ragcrixCustomerNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(ragcrix.getName())));
    }

    @Test
    public void givenCustomer_whenFindAllByOrderByGpaDesc_thenReturnJsonArray() throws Exception {
        given(CustomerService.findAllByOrderByGpaDesc()).willReturn(Arrays.asList(yigit, ragcrix));

        mvc.perform(get("/Customers/orderByGpa/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(yigit.getName())));
    }

    @Test
    public void saveCustomer_itShouldReturnStatusOk() throws Exception {
        given(CustomerService.saveOrUpdateCustomer(any(Customer.class))).willReturn(yigit);

        String jsonString = objectMapper.writeValueAsString(yigit);

        mvc.perform(post("/Customers/save/")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCustomerByCustomerNumber_itShouldReturnStatusOk() throws Exception {
        given(CustomerService.findByCustomerNumber(ragcrixCustomerNumber)).willReturn(ragcrix);
        Mockito.doNothing().when(CustomerService).deleteCustomerById(any(String.class));

        mvc.perform(delete("/Customers/delete/{CustomerNumber}", ragcrixCustomerNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}

