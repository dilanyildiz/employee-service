package com.solviads.employeeservice.service.impl;

import com.solviads.employeeservice.dto.EmployeeDto;
import com.solviads.employeeservice.entity.Employee;
import org.modelmapper.ModelMapper;

public class EmployeeService {

    private ModelMapper modelMapper = new ModelMapper();

    public EmployeeDto convertToDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public Employee convertToEntity(EmployeeDto employeeDTO) {
        return modelMapper.map(employeeDTO, Employee.class);
    }
}

