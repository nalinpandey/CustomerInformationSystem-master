package com.Customer.information.system.service;

import com.Customer.information.system.model.Customer;

import java.util.List;

/**
 * @author regcrix
 */
public interface CustomerService {

    List<Customer> findAll();

    Customer findByCustomerNumber(long CustomerNumber);

    Customer findByEmail(String email);

    List<Customer> findAllByOrderByGpaDesc();

    Customer saveOrUpdateCustomer(Customer Customer);

    void deleteCustomerById(String id);

}

