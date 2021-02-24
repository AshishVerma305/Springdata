package com.example.springdataexample.repository;

import com.example.springdataexample.entity.Department;
import com.example.springdataexample.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByDepartmentName(Department department);
    List<Employee> findByDepartmentName_Id(Long departmentNameId);

    @Query("SELECT e FROM Employee e where e.id=?1")
//    @Query("FROM Employee e where e.departmentName.id=?1")
    List<Employee> getEmployeeListByDepartmentId(Long departmentNameId);
    @Query(value = "select * from employee e where e.department_name_id=?1",nativeQuery = true)
    List<Employee> getEmployeeListByNativeQuery(Long departmentId);
}
