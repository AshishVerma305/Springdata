package com.example.springdataexample.service;

import com.example.springdataexample.dto.DepartmentRequest;
import com.example.springdataexample.dto.DepartmentResponse;
import com.example.springdataexample.dto.EmployeeRequest;
import com.example.springdataexample.dto.EmployeeResponse;
import com.example.springdataexample.entity.Department;

public interface DepartmentService {
    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);
   // DepartmentResponse getDepartmentById(Long id);
    Department getDepartment(Long id);

    DepartmentResponse updateDepartment(Long id,DepartmentRequest departmentRequest);
    DepartmentResponse deleteDepartment(long id);
}
