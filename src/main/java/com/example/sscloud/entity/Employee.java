package com.example.sscloud.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(value = "employee_details")
public class Employee {
    @Id
    private String id;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String dateOfBirth;
    private Department department;
    private String jobTitle;
    private Salary salary;
    private Address address;
    private List<Projects> projects;
    private List<String> skills;
    private String status;
    private String createdAt;
    private String updatedAt;



}
