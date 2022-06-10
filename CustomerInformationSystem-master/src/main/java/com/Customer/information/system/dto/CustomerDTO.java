package com.Customer.information.system.dto;

import java.util.List;

/**
 * @author ragcrix
 */
public class CustomerDTO {

    private String id;
    private String name;
    private long CustomerNumber;
    private String email;
    private List<String> courseList;
    private float gpa;

    public CustomerDTO() {
    }

    public CustomerDTO(String id, String name, long CustomerNumber, String email,
                       List<String> courseList, float gpa) {
        this.id = id;
        this.name = name;
        this.CustomerNumber = CustomerNumber;
        this.email = email;
        this.courseList = courseList;
        this.gpa = gpa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCustomerNumber() {
        return CustomerNumber;
    }

    public void setCustomerNumber(long CustomerNumber) {
        this.CustomerNumber = CustomerNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<String> courseList) {
        this.courseList = courseList;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

}

