package com.example.cms.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.cms.entity.Employee;
import com.example.cms.payload.EmployeeDTO;
import com.example.cms.payload.EmployeeResponse;
import com.example.cms.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class EntityControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    void testGetAllEmployees_Success() {
        // Arrange
        EmployeeResponse employeeResponse  = new EmployeeResponse(); // 初始化 employeeResponse
        EmployeeDTO employeeDTO = new EmployeeDTO(1L,"Alex","Ma","test@email.com");
        employeeResponse.setEmployeeDTOList(Collections.singletonList(employeeDTO));

        when(employeeService.getAllEmployees()).thenReturn(employeeResponse);

        // Act
        ResponseEntity<EmployeeResponse> response = employeeController.getAllEmployees();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeResponse, response.getBody());
    }

    @Test
    void testGetAllEmployees_NoEmployees() {
        // Arrange
        when(employeeService.getAllEmployees()).thenReturn(new EmployeeResponse());

        // Act
        ResponseEntity<EmployeeResponse> response = employeeController.getAllEmployees();

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetEmployeeById_Success() {
        // Arrange
        Employee employee = new Employee(1L,"Alex","Ma","test@email.com");
        EmployeeDTO employeeDTO = new EmployeeDTO(1L,"Alex","Ma","test@email.com");

        when(employeeService.getEmployeeById(1L)).thenReturn(employeeDTO);

        ResponseEntity<EmployeeDTO> response = employeeController.getEmployeeById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTO, response.getBody());
    }

    @Test
    void testCreateEmployee_Success() {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO(null, "Alex", "Ma", "test@email.com");
        EmployeeDTO savedEmployee = new EmployeeDTO(1L, "Alex", "Ma", "test@email.com");

        when(employeeService.createEmployee(employeeDTO)).thenReturn(savedEmployee);

        // Act
        ResponseEntity<EmployeeDTO> response = employeeController.createEmployee(employeeDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedEmployee, response.getBody());
    }

    @Test
    void testUpdateEmployeeById_Success() {
        // Arrange
        Long employeeId = 1L;
        EmployeeDTO employeeDTO = new EmployeeDTO(null, "Alex", "Ma", "test@email.com");
        EmployeeDTO updatedEmployee = new EmployeeDTO(employeeId, "Alex", "Ma", "updated@email.com");

        when(employeeService.updateEmployeeById(employeeDTO, employeeId)).thenReturn(updatedEmployee);

        // Act
        ResponseEntity<EmployeeDTO> response = employeeController.updateEmployeeById(employeeDTO, employeeId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedEmployee, response.getBody());
    }

    @Test
    void testDeleteEmployeeById_Success() {
        // Arrange
        Long employeeId = 1L;
        EmployeeDTO deletedEmployee = new EmployeeDTO(employeeId, "Alex", "Ma", "test@email.com");

        when(employeeService.deleteEmployeeById(employeeId)).thenReturn(deletedEmployee);

        // Act
        ResponseEntity<EmployeeDTO> response = employeeController.deleteEmployeeById(employeeId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(deletedEmployee, response.getBody());
    }
}
