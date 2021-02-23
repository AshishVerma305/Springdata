package com.example.springdataexample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponse {
    private long id;
    private String name;
    private String departmentName;

}
