package com.customer.information.system.repository;

import com.student.information.system.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author ragcrix
 */

// No need implementation, just one interface, and you have CRUD, thanks Spring Data!
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Student findByCustomerNumber(long CustomerNumber);

    Student findByEmail(String email);

    findAllByName();

}
