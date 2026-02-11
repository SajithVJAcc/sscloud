package com.example.sscloud.dto;

import lombok.Data;

@Data
public class EmployeeRequestDto {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String department;
}
