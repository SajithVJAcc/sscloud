package com.example.sscloud.service;

import com.example.sscloud.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getAllEmployees();
    String saveEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getEmployeesByDepartmentAndEmployeeId(String departmentName, String employeeId);
    List<EmployeeDto> getEmployeesBySkill(String skill);
    List<EmployeeDto> getEmployeesByStatus(String status);
    List<EmployeeDto> getEmployeesByJobTitle(String jobTitle);
    List<EmployeeDto> getEmployeesByProject(String projectName);
    List<EmployeeDto> getEmployeesBySalaryRange(double minSalary, double maxSalary);
    List<EmployeeDto> getEmployeesByName(String name, String lastName);
    List<EmployeeDto> getEmployeesByIdNameStatus(String employeeId, String name, String status);
}
