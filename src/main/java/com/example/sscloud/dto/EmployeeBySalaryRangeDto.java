package com.example.sscloud.dto;

import lombok.Data;

import java.util.List;
@Data
public class EmployeeBySalaryRangeDto {
    String range;
    Integer employeeCount;
    String level;
    List<EmployeeDto> employees;
}
