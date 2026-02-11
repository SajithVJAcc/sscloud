package com.example.sscloud.controller;

import com.example.sscloud.dto.*;
import com.example.sscloud.exception.AppException;
import com.example.sscloud.service.BooksServiceImpl;
import com.example.sscloud.service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DemoController {
    @Autowired
    private BooksServiceImpl booksService;
    @Autowired
    private EmployeeServiceImpl employeeService;
    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>(

                "Hello, SSCloud!" , HttpStatus.OK);
    }
    @GetMapping("/getBooks")
    public ResponseEntity<List<BookDto>> getBooks(){
        List<BookDto> bookDtoList= booksService.getAllBooksInLibrary();
        return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
    }
    @GetMapping("/getBooksByName")
    public ResponseEntity<List<BookDto>> getBooksByName(@RequestParam(required = true) String name){
        if (name==null || name.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            List<BookDto> bookDtoList = booksService.getAllBooksBooksByName(name);
            if (bookDtoList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
        }
    }
    @PostMapping("/getEmployeeDetails")
    public ResponseEntity<List<EmployeeDto>> getEmployeeDetails(@RequestBody EmployeeRequestDto employeeDto){
        String employeeId =employeeDto.getEmployeeId();
        String name =employeeDto.getFirstName();
        String lastName=employeeDto.getLastName();
//        List<EmployeeDto> employeeDtos = employeeService.getEmployeesByName(name, lastName);

        List<EmployeeDto> employeeDtos = employeeService.getEmployeesByIdNameStatus(employeeId, name, "Active");
        if (employeeDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeDtos , HttpStatus.OK);
    }
    @PostMapping("/details")
    public ResponseEntity<List<EmployeeDto>> details(@RequestBody EmployeeRequestDto employeeDto){
        String employeeId =employeeDto.getEmployeeId();
        String name =employeeDto.getDepartment();
        List<EmployeeDto> employeeDtoList= employeeService.getEmployeesByDepartmentAndEmployeeId(name, employeeId);
        return new ResponseEntity<>(employeeDtoList , HttpStatus.OK);

    }

    @PostMapping("/detailsWithSalary")
    public ResponseEntity<List<EmployeeBySalaryRangeDto>> detailsWithSalary(@Valid @RequestBody SalaryRequestDto employeeDto) {
        if(isValid(employeeDto)) {
            String firstName = employeeDto.getFirstName();
            String name = employeeDto.getDepartment();

            List<EmployeeBySalaryRangeDto> employeeDtoList = employeeService.getEmployeesGroupedBySalaryRange(name, firstName);
            if (!employeeDtoList.isEmpty()) {
                return new ResponseEntity<>(employeeDtoList, HttpStatus.OK);
            } else {
                throw new AppException(HttpStatus.NOT_FOUND.name(), "No employees found for the given criteria.");
            }
        } else {
            throw new AppException(HttpStatus.BAD_REQUEST.name(), "At least one of 'firstName' or 'department' must be provided.");

        }
    }

        @PostMapping("/submit")
        public ResponseEntity<String> submit () {
            return new ResponseEntity<>("Submission successful!", HttpStatus.OK);
        }

        @PutMapping("/insertOrUpdate")
        public ResponseEntity<String> update (@RequestBody EmployeeDto employeeInsertDto){
            try {
                if (employeeInsertDto.getEmployeeId() == null || employeeInsertDto.getEmployeeId().isEmpty()) {
                    throw new AppException("BAD_REQUEST", "Employee ID is required for update.");
                }
                employeeService.saveEmployee(employeeInsertDto);

            } catch (AppException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>("Update successful!", HttpStatus.OK);
        }

        @PatchMapping("/modify")
        public ResponseEntity<String> modify () {
            return new ResponseEntity<>("Modification successful!", HttpStatus.OK);
        }

        @DeleteMapping("/remove")
        public ResponseEntity<String> remove () {
            return new ResponseEntity<>("Removal successful!", HttpStatus.OK);
        }

        @RequestMapping("/any")
        public ResponseEntity<String> anyMethod () {
            return new ResponseEntity<>("Any method accepted!", HttpStatus.OK);
        }

    private boolean isValid(SalaryRequestDto dto) {
        if (dto != null) {
            String firstName = dto.getFirstName();
            String department = dto.getDepartment();
            return (firstName != null && !firstName.isEmpty()) || (department != null && !department.isEmpty());
        } else {
            return false;
        }
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidParameter(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .badRequest()
                .body("Invalid parameter provided: " + ex.getMessage());
    }


}
