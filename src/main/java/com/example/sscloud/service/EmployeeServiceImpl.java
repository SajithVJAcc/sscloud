package com.example.sscloud.service;

import com.example.sscloud.dto.EmployeeBySalaryRangeDto;
import com.example.sscloud.dto.EmployeeDto;
import com.example.sscloud.entity.Employee;
import com.example.sscloud.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SalaryService salaryService;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> employeeDtoList= new java.util.ArrayList<>();
        employeeRepository.findAll().forEach(employee -> {
            EmployeeDto employeeDto=new EmployeeDto();
            modelMapper.map(employee,employeeDto);
            // Mapping logic here

          employeeDtoList.add(employeeDto);
        });

        return employeeDtoList;
    }

    @Override
    public String saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        modelMapper.map(employeeDto,employee);
        if (employee.getId()==null) {
            employee.setCreatedAt(dateToString());
            employee.setUpdatedAt(dateToString());
        }
        else {
            employee.setUpdatedAt(dateToString());
        }

        try {
            employeeRepository.save(employee);
        } catch (Exception e) {
            return "Error saving employee: " + e.getMessage();
        }

        return "Successfully saved employee with ID: " + employee.getEmployeeId();
    }

    @Override
    public List<EmployeeDto> getEmployeesByDepartmentAndEmployeeId(String departmentName, String employeeId) {
        List<EmployeeDto> employeeDtoList= new java.util.ArrayList<>();
        employeeRepository.findByDepartmentNameAndEmployeeId(departmentName,employeeId).forEach(employee -> {
            EmployeeDto employeeDto=new EmployeeDto();
            modelMapper.map(employee,employeeDto);

            // Mapping logic here

            employeeDtoList.add(employeeDto);
        });

        return employeeDtoList;
    }


    @Override
    public List<EmployeeDto> getEmployeesBySkill(String skill) {
        return List.of();
    }

    @Override
    public List<EmployeeDto> getEmployeesByStatus(String status) {
        return List.of();
    }

    @Override
    public List<EmployeeDto> getEmployeesByJobTitle(String jobTitle) {
        return List.of();
    }

    @Override
    public List<EmployeeDto> getEmployeesByProject(String projectName) {
        return List.of();
    }

    @Override
    public List<EmployeeDto> getEmployeesBySalaryRange(double minSalary, double maxSalary) {
        return List.of();
    }

    @Override
    public List<EmployeeDto> getEmployeesByName(String name, String lastName) {
        List<EmployeeDto> employeeDtoList=employeeRepository.findByFirstNameAndLastNameContaining(name,lastName).stream().map(employee -> {
            EmployeeDto employeeDto=new EmployeeDto();
            modelMapper.map(employee,employeeDto);
            // Mapping logic here

            return employeeDto;
        }).toList();
//        List<EmployeeDto> employeeDtoList= new ArrayList<>();
//       employeeRepository.findAll().forEach(System.out::println);

        return employeeDtoList;
//        return List.of();
    }

    @Override
    public List<EmployeeDto> getEmployeesByIdNameStatus(String employeeId, String name, String status) {

        List<EmployeeDto> employeeDtoList=employeeRepository.findByEmployeeIdOrFirstNameAndStatusStartsWith(employeeId,name,status).stream().map(employee -> {
            EmployeeDto employeeDto=new EmployeeDto();
            modelMapper.map(employee,employeeDto);
            // Mapping logic here

            return employeeDto;
        }).toList();
        return employeeDtoList;
    }

    public List<EmployeeBySalaryRangeDto> getEmployeesGroupedBySalaryRange(String name, String firStName) {


        Integer rangeCount = salaryService.getRangeCount();
        List<Employee> employeeList;
        if (name==null||name.isEmpty()) {
           employeeList=employeeRepository.findByFirstNameAndStatusStartsWith(firStName,"Active");
        } else if (firStName==null||firStName.isEmpty()) {
           employeeList=employeeRepository.findByDepartmentNameAndStatusStartsWith(name,"Active");

        }
        else {
            employeeList = employeeRepository.findByDepartmentNameAndFirstNameAndStatusStartsWith(name, firStName, "Active");
        }
        List<EmployeeBySalaryRangeDto> salaryRangeDtoList= createSalaryRangeGroups(employeeList, rangeCount);
        return salaryRangeDtoList;
    }
    private List<EmployeeBySalaryRangeDto> createSalaryRangeGroups(List<Employee> employeeList, int rangeSize) {
        List<EmployeeBySalaryRangeDto> groupedList = new java.util.ArrayList<>();
        for (int i = 0; i < rangeSize; i ++) {
            EmployeeBySalaryRangeDto dto ;
            String range = salaryService.getSalaryRange(i+1);// Example: "50000-70000"
            String[] parts = range.split("-");
            Integer minSalary = Integer.parseInt(parts[0]);
            int maxSalary = Integer.parseInt(parts[1]);
            List<EmployeeDto> employees = employeeList.stream().filter(x->x.getSalary().getAmount()>=minSalary && x.getSalary().getAmount()<=maxSalary).map(employee -> {
                EmployeeDto employeeDto = new EmployeeDto();
                modelMapper.map(employee, employeeDto);
                return employeeDto;
            }).toList();
            if (!employees.isEmpty()) {
                dto = new EmployeeBySalaryRangeDto();
                dto.setRange(range);
                dto.setEmployeeCount(employees.size());
                dto.setLevel("Level " + (i + 1));
                dto.setEmployees(employees);
                groupedList.add(dto);
            }
        }
        return groupedList;
    }

    private String dateToString() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss 'UTC' yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;

        return now.format(formatter);

    }
}
