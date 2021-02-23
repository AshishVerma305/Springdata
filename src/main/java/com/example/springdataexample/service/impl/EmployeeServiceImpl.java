package com.example.springdataexample.service.impl;

import com.example.springdataexample.dto.EmployeeRequest;
import com.example.springdataexample.dto.EmployeeResponse;
import com.example.springdataexample.entity.Employee;
import com.example.springdataexample.repository.EmployeeRepository;
import com.example.springdataexample.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee employee=new Employee();
        BeanUtils.copyProperties(employeeRequest,employee);
        Employee saveEmployee=employeeRepository.save(employee);

        EmployeeResponse response= new EmployeeResponse();
        BeanUtils.copyProperties(saveEmployee,response);
        return response;
    }
    @Override
    public EmployeeResponse getEmployeeById(Long Id)
    {
        Optional<Employee> employeeOptional=employeeRepository.findById(Id);
        if(employeeOptional.isPresent())
        {
            EmployeeResponse employeeResponse=new EmployeeResponse();
            BeanUtils.copyProperties(employeeOptional.get(),employeeResponse);
            return employeeResponse;
        }
        return null;
    }

    @Override
    public EmployeeResponse updateEmployeeById(Long id, EmployeeRequest employeeRequest) {
        Optional<Employee> employeeOptional=employeeRepository.findById(id);
        if(employeeOptional.isPresent())
        {
            Employee employeeFromDb=employeeOptional.get();
            employeeFromDb.setName(employeeRequest.getName());
            employeeFromDb.setDepartmentName(employeeRequest.getDepartmentName());
            Employee savedEmployee = employeeRepository.save(employeeFromDb);
            EmployeeResponse response=new EmployeeResponse();
            BeanUtils.copyProperties(savedEmployee,response);
            return response;
        }
        return null;
    }
    public EmployeeResponse deleteEmployeeById(long id)
    {
        Optional<Employee> employeeOptional=employeeRepository.findById(id);
        if(employeeOptional.isPresent())
        {
            Employee employeeFromDb=employeeOptional.get();
            employeeRepository.deleteById(id);
            EmployeeResponse response=new EmployeeResponse();
            BeanUtils.copyProperties(employeeFromDb,response);
            return response;
        }
        return null;
    }
}
