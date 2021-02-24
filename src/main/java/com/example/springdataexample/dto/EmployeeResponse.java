package com.example.springdataexample.dto;

import com.example.springdataexample.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponse {
    private long id;
    private String name;
    private String code;
    private DepartmentResponse departmentName;

    public void setDepartmentFromEntity(Department department) {
        DepartmentResponse departmentResponse=new DepartmentResponse();
        departmentResponse.setId(department.getId());
        departmentResponse.setName(department.getName());
        this.departmentName=departmentResponse;
    }
}
