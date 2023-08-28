
package com.solviads.employeeservice.controller;

import com.solviads.employeeservice.dto.EmployeeDto;
import com.solviads.employeeservice.entity.Employee;
import com.solviads.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto createdEmployeeDto = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployeeDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDto employeeDto) {
        Employee resultEmployee = employeeService.updateEmployee(id,employeeDto);
        return ResponseEntity.ok(resultEmployee);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployee() {
        List<EmployeeDto> employees = employeeService.getEmployee();
        List<EmployeeDto> employeeDtos = employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeDtos);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployee(id);
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("/delete/{userid}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable("userid") Long id) {
        Boolean status = employeeService.deleteEmployee(id);
        return ResponseEntity.ok(status);
    }
}
