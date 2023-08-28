package com.solviads.employeeservice.service.impl;

import com.solviads.employeeservice.dto.EmployeeDto;
import com.solviads.employeeservice.entity.Employee;
import com.solviads.employeeservice.exception.EmployeeCreationOperationException;
import com.solviads.employeeservice.repository.EmployeeRepository;
import com.solviads.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        try {
            Employee employee = modelMapper.map(employeeDto, Employee.class);
            Employee savedEmployee = employeeRepository.save(employee);
            return modelMapper.map(savedEmployee, EmployeeDto.class);
        } catch (Exception e) {
            throw new EmployeeCreationOperationException(e.getMessage());
        }
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDto employeeDto) {
        try{
            Optional<Employee> resultEmployee = employeeRepository.findById(id);


            if (resultEmployee.isPresent()) {
                Employee existingEmployee = resultEmployee.get();
                modelMapper.map(employeeDto, existingEmployee);
                return employeeRepository.save(existingEmployee);
            }

            throw new EmployeeCreationOperationException(String.format("Employee didn't find with this ID: %s " ,id));


        }catch (Exception e){
            throw new EmployeeCreationOperationException(e.getMessage());
        }


    }

    @Override
    public List<EmployeeDto> getEmployee() {

        try{
            List<Employee> employees = employeeRepository.findAll();
            List<EmployeeDto> dtos = employees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
            return dtos;

        }catch (Exception e){
            throw new EmployeeCreationOperationException(e.getMessage());
        }

    }


    @Override
    public Employee getEmployee(Long id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(() ->
                        new EmployeeCreationOperationException(String.format("Employee did not find with this ID: %s " , id)));
    }

    @Override
    public Boolean deleteEmployee(Long id) {
        try{

            var employee = employeeRepository.findById(id)
                    .orElseThrow(() ->
                            new EmployeeCreationOperationException(String.format("Employee did not find with this ID: %s " , id)));
            employeeRepository.deleteById(id);
            return true;

        }catch (Exception e){
            throw new EmployeeCreationOperationException(e.getMessage());
        }

    }
}
