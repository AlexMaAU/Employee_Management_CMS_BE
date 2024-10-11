package com.example.cms.service;

import com.example.cms.entity.Employee;
import com.example.cms.payload.EmployeeDTO;
import com.example.cms.payload.EmployeeResponse;
import com.example.cms.respository.EmployeeRepo;
import com.example.cms.service.Implementation.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void testGetAllEmployees() {
        Employee employee = new Employee();
        EmployeeDTO employeeDTO = new EmployeeDTO();

        when(employeeRepo.findAll()).thenReturn(Collections.singletonList(employee));
        when(modelMapper.map(employee, EmployeeDTO.class)).thenReturn(employeeDTO);

        EmployeeResponse employeeResponse = employeeService.getAllEmployees();

        assertNotNull(employeeResponse);
        assertEquals(1, employeeResponse.getEmployeeDTOList().size());
        assertEquals(employeeDTO, employeeResponse.getEmployeeDTOList().get(0));
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = new Employee(1L,"Alex","Ma","test@email.com");
        EmployeeDTO employeeDTO = new EmployeeDTO(1L,"Alex","Ma","test@email.com");

        when(employeeRepo.findById(1L)).thenReturn(Optional.of(employee));
        when(modelMapper.map(employee, EmployeeDTO.class)).thenReturn(employeeDTO);

        EmployeeDTO findEmployeeDTO = employeeService.getEmployeeById(1L);

        assertNotNull(findEmployeeDTO);
        assertEquals(1L, findEmployeeDTO.getId());
        assertEquals("Alex", findEmployeeDTO.getFirstName());
        assertEquals("Ma", findEmployeeDTO.getLastName());
        assertEquals("test@email.com", findEmployeeDTO.getEmail());
    }

    @Test
    public void testCreateEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee = new Employee();

        when(modelMapper.map(employeeDTO, Employee.class)).thenReturn(employee);
        when(employeeRepo.save(employee)).thenReturn(employee);
        when(modelMapper.map(employee, EmployeeDTO.class)).thenReturn(employeeDTO);

        EmployeeDTO savedEmployee = employeeService.createEmployee(employeeDTO);

        assertNotNull(savedEmployee);
        assertEquals(employeeDTO, savedEmployee);
        verify(employeeRepo).save(employee);
        verify(modelMapper).map(employeeDTO, Employee.class);
        verify(modelMapper).map(employee, EmployeeDTO.class);
    }

    @Test
    public void testUpdateEmployeeById() {
        Employee existingEmployee = new Employee(1L, "John", "Doe", "john.doe@example.com");
        EmployeeDTO employeeDTO = new EmployeeDTO(1L, "John", "Doe", "john.doe@example.com");

        // Arrange
        when(employeeRepo.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(modelMapper.map(existingEmployee, EmployeeDTO.class)).thenReturn(employeeDTO);

        // Act
        EmployeeDTO updatedEmployee = employeeService.updateEmployeeById(employeeDTO, 1L);

        // assert
        verify(employeeRepo).findById(1L);
        verify(employeeRepo).save(existingEmployee);
        assertEquals("John", existingEmployee.getFirstName());
        assertEquals("Doe", existingEmployee.getLastName());
        assertEquals("john.doe@example.com", existingEmployee.getEmail());
        assertEquals(employeeDTO, updatedEmployee);
    }

    @Test
    public void testDeleteEmployeeById() {
        Employee existingEmployee = new Employee(1L, "John", "Doe", "john.doe@example.com");
        EmployeeDTO employeeDTO = new EmployeeDTO(1L, "John", "Doe", "john.doe@example.com");

        // Arrange
        when(employeeRepo.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(modelMapper.map(existingEmployee, EmployeeDTO.class)).thenReturn(employeeDTO);

        // Act
        EmployeeDTO deletedEmployee = employeeService.deleteEmployeeById(1L);

        // Assert
        verify(employeeRepo).findById(1L);
        verify(employeeRepo).deleteById(1L);
        assertEquals(employeeDTO, deletedEmployee);
    }
}
