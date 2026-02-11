package com.example.sscloud.dto;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Data

public class EmployeeDto {

    private String id;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String dateOfBirth;
    private DepartmentDto department;
    private String jobTitle;
    private SalaryDto salary;
    private AddressDto address;
    private List<ProjectsDto> projects;
    private List<String> skills;
    private String status;
    private String createdAt;
    private String updatedAt;

}
