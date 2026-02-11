package com.example.sscloud.repository;

import com.example.sscloud.entity.Employee;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, ObjectId> {
    List<Employee> findByFirstNameAndLastNameContaining(String firstName,String lastName);
    List<Employee> findByEmployeeIdOrFirstNameAndStatusStartsWith(String employeeId, String firstName, String status);
    List<Employee>findByDepartmentNameAndEmployeeId(String name,String employeeId);
    List<Employee>findByDepartmentNameAndFirstNameAndStatusStartsWith(String name,String firstName, String status);
    List<Employee>findByFirstNameAndStatusStartsWith(String firstName, String status);
    List<Employee>findByDepartmentNameAndStatusStartsWith(String name, String status);
}
