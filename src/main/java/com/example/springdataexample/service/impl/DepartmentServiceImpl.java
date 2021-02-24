package com.example.springdataexample.service.impl;

import com.example.springdataexample.dto.DepartmentRequest;
import com.example.springdataexample.dto.DepartmentResponse;
import com.example.springdataexample.dto.EmployeeRequest;
import com.example.springdataexample.dto.EmployeeResponse;
import com.example.springdataexample.entity.Department;
import com.example.springdataexample.entity.Employee;
import com.example.springdataexample.repository.DepartmentRepository;
import com.example.springdataexample.repository.EmployeeRepository;
import com.example.springdataexample.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired EmployeeRepository employeeRepository;
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest)
    {
        Department department=new Department();
        BeanUtils.copyProperties(departmentRequest,department);
        Department saveDepartment=departmentRepository.save(department);

        DepartmentResponse response= new DepartmentResponse();
        BeanUtils.copyProperties(saveDepartment,response);
        return response;
    }
   /* public DepartmentResponse getDepartmentById(Long id)
    {
        Optional<Department> departmentOptional=departmentRepository.findById(id);
        if(departmentOptional.isPresent())
        {
            DepartmentResponse departmentResponse=new DepartmentResponse();
            BeanUtils.copyProperties(departmentOptional.get(),departmentResponse);
            return departmentResponse;
        }
        return null;
    }*/
    @Override
    @Transactional
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest departmentRequest)
    {
        Department department=departmentRepository.findById(id).get();
        List<Employee> employeeList=employeeRepository.findByDepartmentName_Id(id);
        department.setName(departmentRequest.getName());
        Department savedDepartment =departmentRepository.save(department);
        for(Employee e: employeeList)
        {
            e.setCode(departmentRequest.getDepartmentCode());
        }
        employeeRepository.saveAll(employeeList);
        DepartmentResponse departmentResponse=new DepartmentResponse();
        BeanUtils.copyProperties(savedDepartment,departmentResponse);
        return departmentResponse;
    }
    public DepartmentResponse deleteDepartment(long id)
    {
        Optional<Department> departmentOptional=departmentRepository.findById(id);
        if(departmentOptional.isPresent())
        {
            Department departmentFromDb=departmentOptional.get();
            departmentRepository.deleteById(id);
            DepartmentResponse Response=new DepartmentResponse();
            BeanUtils.copyProperties(departmentFromDb,Response);
            return Response;
        }
        return null;
    }
    public Department getDepartment(Long id)
    {
       Department department=departmentRepository.findById(id).get();
      return department;
   }

}
