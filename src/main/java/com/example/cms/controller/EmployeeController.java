package com.example.cms.controller;

import com.example.cms.payload.EmployeeDTO;
import com.example.cms.payload.EmployeeResponse;
import com.example.cms.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<EmployeeResponse> getAllEmployees() {
        EmployeeResponse employeeResponse = employeeService.getAllEmployees();

        if(employeeResponse == null || employeeResponse.getEmployeeDTOList() == null || employeeResponse.getEmployeeDTOList().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);

        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO savedEmployee = employeeService.createEmployee(employeeDTO);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable Long id) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployeeById(employeeDTO, id);

        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployeeById(@PathVariable Long id) {
        EmployeeDTO deletedEmployee = employeeService.deleteEmployeeById(id);

        return new ResponseEntity<>(deletedEmployee, HttpStatus.OK);
    }

}
