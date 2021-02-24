package com.example.springdataexample.service.impl;

import com.example.springdataexample.dto.DepartmentResponse;
import com.example.springdataexample.dto.EmployeeRequest;
import com.example.springdataexample.dto.EmployeeResponse;
import com.example.springdataexample.entity.Department;
import com.example.springdataexample.entity.Employee;
import com.example.springdataexample.repository.DepartmentRepository;
import com.example.springdataexample.repository.EmployeeRepository;
import com.example.springdataexample.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee employee=new Employee();

        BeanUtils.copyProperties(employeeRequest,employee);
        Optional<Department> departmentOptional=departmentRepository.findById(employeeRequest.getDepartmentName().getId());
        if(departmentOptional.isPresent())
        {
            employee.setDepartmentName(departmentOptional.get());
        }
        else {
            Department department=new Department();
            department.setName(employeeRequest.getDepartmentName().getName());
            employee.setDepartmentName(department);
        }
        Employee saveEmployee=employeeRepository.save(employee);

        EmployeeResponse response= new EmployeeResponse();
        BeanUtils.copyProperties(saveEmployee,response);
        response.setDepartmentFromEntity(employee.getDepartmentName());
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
            employeeResponse.setDepartmentFromEntity(employeeOptional.get().getDepartmentName());
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
            Optional<Department> departmentOptional=departmentRepository.findById(employeeRequest.getDepartmentName().getId());
            if(departmentOptional.isPresent())
            {
                employeeFromDb.setDepartmentName(departmentOptional.get());
            }
            else {
                Department department=new Department();
                department.setName(employeeRequest.getDepartmentName().getName());
                employeeFromDb.setDepartmentName(department);
            }
            Employee savedEmployee = employeeRepository.save(employeeFromDb);
            EmployeeResponse response=new EmployeeResponse();
            BeanUtils.copyProperties(savedEmployee,response);
            response.setDepartmentFromEntity(savedEmployee.getDepartmentName());
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
            response.setDepartmentFromEntity(employeeFromDb.getDepartmentName());
            return response;
        }
        return null;
    }
    public List<EmployeeResponse> getEmployeeListByDepartment(long departmentId)
    {
       // Department department=departmentRepository.findById(departmentId).get();
       // List<Employee> employeeList=employeeRepository.findByDepartmentName(department);
       // List<Employee> employeeList=employeeRepository.findByDepartmentName_Id(departmentId);
       // List<Employee> employeeList= employeeRepository.(departmentId);
        List<Employee> employeeList= employeeRepository.getEmployeeListByNativeQuery(departmentId);
        List<EmployeeResponse> employeeResponseList=new ArrayList<>();
        for(Employee employee:employeeList)
        {
            EmployeeResponse response= new EmployeeResponse();
            BeanUtils.copyProperties(employee,response);
            response.setDepartmentFromEntity(employee.getDepartmentName());
            employeeResponseList.add(response);
        }

        return employeeResponseList;
    }

}
