package com.example.springdataexample.controller;

import com.example.springdataexample.dto.EmployeeRequest;
import com.example.springdataexample.dto.EmployeeResponse;
import com.example.springdataexample.service.EmployeeService;
import com.example.springdataexample.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest employeeRequest)
    {
        return employeeService.createEmployee(employeeRequest);
    }
    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Long id)
    {
        return employeeService.getEmployeeById(id);
    }
    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable("id") Long id,@RequestBody EmployeeRequest employeeRequest)
    {
        return employeeService.updateEmployeeById(id,employeeRequest);
    }
    @DeleteMapping("/{id}")
    public EmployeeResponse deleteEmployee(@PathVariable("id") Long id)
    {
        return employeeService.deleteEmployeeById(id);
    }
}
