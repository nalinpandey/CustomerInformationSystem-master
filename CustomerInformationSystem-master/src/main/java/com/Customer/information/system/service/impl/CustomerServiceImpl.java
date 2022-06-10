package com.Customer.information.system.service.impl;

import com.Customer.information.system.model.Customer;
import com.Customer.information.system.repository.CustomerRepository;
import com.Customer.information.system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ragcrix
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository CustomerRepository;

    @Override
    public List<Customer> findAll() {
        return CustomerRepository.findAll();
    }

    @Override
    public Customer findByCustomerNumber(long CustomerNumber) {
        return CustomerRepository.findByCustomerNumber(CustomerNumber);
    }

    @Override
    public Customer findByEmail(String email) {
        return CustomerRepository.findByEmail(email);
    }

    @Override
    public List<Customer> findAllByOrderByGpaDesc() {
        return CustomerRepository.findAllByOrderByGpaDesc();
    }

    @Override
    public Customer saveOrUpdateCustomer(Customer Customer) {
        return CustomerRepository.save(Customer);
    }

    @Override
    public void deleteCustomerById(String id) {
        CustomerRepository.deleteById(id);
    }
}

