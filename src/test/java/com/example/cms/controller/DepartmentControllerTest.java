package com.example.cms.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.cms.entity.Department;
import com.example.cms.payload.DepartmentDTO;
import com.example.cms.payload.DepartmentResponse;
import com.example.cms.service.DepartmentService;
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
public class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DepartmentController departmentController;

    @Test
    void testGetAllDepartments_Success() {
        // Arrange
        DepartmentResponse departmentResponse  = new DepartmentResponse(); // 初始化 departmentResponse
        DepartmentDTO departmentDTO = new DepartmentDTO(1L,"HR","AAAA");
        departmentResponse.setDepartmentDTOList(Collections.singletonList(departmentDTO));

        when(departmentService.getAllDepartments()).thenReturn(departmentResponse);

        // Act
        ResponseEntity<DepartmentResponse> response = departmentController.getAllDepartments();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departmentResponse, response.getBody());
    }

    @Test
    void testGetDepartmentById_Success() {
        // Arrange
        Department department = new Department(1L,"HR","AAAA");
        DepartmentDTO departmentDTO = new DepartmentDTO(1L,"HR","AAAA");

        when(departmentService.getDepartmentById(1L)).thenReturn(departmentDTO);

        ResponseEntity<DepartmentDTO> response = departmentController.getDepartmentById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departmentDTO, response.getBody());
    }

    @Test
    void testCreateDepartment_Success() {
        // Arrange
        DepartmentDTO departmentDTO = new DepartmentDTO(1L,"HR","AAAA");
        DepartmentDTO savedDepartment = new DepartmentDTO(1L,"HR","AAAA");

        when(departmentService.createDepartment(departmentDTO)).thenReturn(savedDepartment);

        // Act
        ResponseEntity<DepartmentDTO> response = departmentController.createDepartment(departmentDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedDepartment, response.getBody());
    }

    @Test
    void testUpdateDepartmentById_Success() {
        // Arrange
        Long departmentId = 1L;
        DepartmentDTO departmentDTO = new DepartmentDTO(1L,"HR","AAAA");
        DepartmentDTO updatedDepartment = new DepartmentDTO(departmentId, "HR","AAAA");

        when(departmentService.updateDepartmentById(departmentDTO, departmentId)).thenReturn(updatedDepartment);

        // Act
        ResponseEntity<DepartmentDTO> response = departmentController.updateDepartmentById(departmentDTO, departmentId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedDepartment, response.getBody());
    }

    @Test
    void testDeleteDepartmentById_Success() {
        // Arrange
        Long departmentId = 1L;
        DepartmentDTO deletedDepartment = new DepartmentDTO(departmentId, "HR","AAAA");

        when(departmentService.deleteDepartmentById(departmentId)).thenReturn(deletedDepartment);

        // Act
        ResponseEntity<DepartmentDTO> response = departmentController.deleteDepartmentById(departmentId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(deletedDepartment, response.getBody());
    }
}
