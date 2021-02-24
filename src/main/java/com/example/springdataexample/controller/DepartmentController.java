package com.example.springdataexample.controller;

import com.example.springdataexample.dto.DepartmentRequest;
import com.example.springdataexample.dto.DepartmentResponse;
import com.example.springdataexample.dto.EmployeeRequest;
import com.example.springdataexample.dto.EmployeeResponse;
import com.example.springdataexample.entity.Department;
import com.example.springdataexample.service.DepartmentService;
import com.example.springdataexample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController{
    @Autowired
    private DepartmentService departmentService;
    @PostMapping
    public DepartmentResponse createDepartment(@RequestBody DepartmentRequest departmentRequest)
    {
        return departmentService.createDepartment(departmentRequest);
    }
    //@GetMapping("{id}")
   // public DepartmentResponse getDepartmentById(@PathVariable Long id)
    //{
    //    return departmentService.getDepartmentById(id);
   // }
    @PutMapping("/{id}")
    public DepartmentResponse updateDepartment(@PathVariable("id") Long id,@RequestBody DepartmentRequest departmentRequest)
    {
        return departmentService.updateDepartment(id,departmentRequest);
    }
    @DeleteMapping("/{id}")
    public DepartmentResponse deleteDepartment(@PathVariable("id") Long id)
    {
        return departmentService.deleteDepartment(id);
    }
    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable("id") long id)
    {
        return departmentService.getDepartment(id);
    }

}
