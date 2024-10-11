package com.example.cms.service;

import com.example.cms.payload.EmployeeDTO;
import com.example.cms.payload.EmployeeResponse;

public interface EmployeeService {
    EmployeeResponse getAllEmployees();

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO, Long id);

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO deleteEmployeeById(Long id);
}
