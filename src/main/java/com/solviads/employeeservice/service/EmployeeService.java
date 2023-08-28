package com.solviads.employeeservice.service;

import com.solviads.employeeservice.dto.EmployeeDto;
import com.solviads.employeeservice.entity.Employee;

import java.util.List;


public interface EmployeeService {



    public EmployeeDto createEmployee(EmployeeDto employeeDto);


    public Employee updateEmployee(Long id, EmployeeDto updatedEmployeeDto);

    List<EmployeeDto> getEmployee();

    Employee getEmployee(Long id);
    Boolean deleteEmployee(Long id);

}
