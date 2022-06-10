package com.customer.information.system.controller;

import com.customer.information.system.dto.CustomerDTO;
import com.customer.information.system.model.Customer;
import com.customer.information.system.service.CustomerService;
import com.customer.information.system.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ragcrix
 */
@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    @Autowired
    private CustomerService CustomerService;

    @GetMapping(value = "/")
    public List<CustomerDTO> getAllCustomers() {
        return ObjectMapperUtils.mapAll(CustomerService.findAll(), CustomerDTO.class);
    }

    @GetMapping(value = "/byCustomerNumber/{CustomerNumber}")
    public CustomerDTO getCustomerByCustomerNumber(@PathVariable("CustomerNumber") Long CustomerNumber) {
        return ObjectMapperUtils.map(CustomerService.findByCustomerNumber(CustomerNumber), CustomerDTO.class);
    }

    @GetMapping(value = "/byEmail/{email}")
    public CustomerDTO getCustomerByEmail(@PathVariable("email") String email) {
        return ObjectMapperUtils.map(CustomerService.findByEmail(email), CustomerDTO.class);
    }

    @GetMapping(value = "/orderByGpa")
    public List<CustomerDTO> findAllByOrderByGpaDesc() {
        return ObjectMapperUtils.mapAll(CustomerService.findAllByOrderByGpaDesc(), CustomerDTO.class);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveOrUpdateCustomer(@RequestBody CustomerDTO CustomerDTO) {
        CustomerService.saveOrUpdateCustomer(ObjectMapperUtils.map(CustomerDTO, Customer.class));
        return new ResponseEntity("Customer added successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{CustomerNumber}")
    public ResponseEntity<?> deleteCustomerByCustomerNumber(@PathVariable long CustomerNumber) {
        CustomerService.deleteCustomerById(CustomerService.findByCustomerNumber(CustomerNumber).getId());
        return new ResponseEntity("Customer deleted successfully", HttpStatus.OK);
    }

}

