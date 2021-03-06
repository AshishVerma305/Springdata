package com.example.springdataexample.service;

import com.example.springdataexample.dto.EmployeeRequest;
import com.example.springdataexample.dto.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);
    EmployeeResponse getEmployeeById(Long id);
    EmployeeResponse updateEmployeeById(Long id,EmployeeRequest employeeRequest);
    EmployeeResponse deleteEmployeeById(long id);

    List<EmployeeResponse> getEmployeeListByDepartment(long departmentId);
}
