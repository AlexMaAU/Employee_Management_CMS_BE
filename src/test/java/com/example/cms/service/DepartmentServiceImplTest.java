package com.example.cms.service;

import com.example.cms.entity.Department;
import com.example.cms.payload.DepartmentDTO;
import com.example.cms.payload.DepartmentResponse;
import com.example.cms.respository.DepartmentRepo;
import com.example.cms.service.Implementation.DepartmentServiceImpl;
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
public class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepo departmentRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    public void testGetAllDepartments() {
        Department department = new Department();
        DepartmentDTO departmentDTO = new DepartmentDTO();

        when(departmentRepo.findAll()).thenReturn(Collections.singletonList(department));
        when(modelMapper.map(department, DepartmentDTO.class)).thenReturn(departmentDTO);

        DepartmentResponse departmentResponse = departmentService.getAllDepartments();

        assertNotNull(departmentResponse);
        assertEquals(1, departmentResponse.getDepartmentDTOList().size());
        assertEquals(departmentDTO, departmentResponse.getDepartmentDTOList().get(0));
    }

    @Test
    public void testGetDepartmentById() {
        Department department = new Department(1L,"HR","AAAA");
        DepartmentDTO departmentDTO = new DepartmentDTO(1L,"HR","AAAA");

        when(departmentRepo.findById(1L)).thenReturn(Optional.of(department));
        when(modelMapper.map(department, DepartmentDTO.class)).thenReturn(departmentDTO);

        DepartmentDTO findDepartmentDTO = departmentService.getDepartmentById(1L);

        assertNotNull(findDepartmentDTO);
        assertEquals(1L, findDepartmentDTO.getId());
        assertEquals("HR", findDepartmentDTO.getDepartmentName());
        assertEquals("AAAA", findDepartmentDTO.getDepartmentDescription());
    }

    @Test
    public void testCreateDepartment() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        Department department = new Department();

        when(modelMapper.map(departmentDTO, Department.class)).thenReturn(department);
        when(departmentRepo.save(department)).thenReturn(department);
        when(modelMapper.map(department, DepartmentDTO.class)).thenReturn(departmentDTO);

        DepartmentDTO savedDepartment = departmentService.createDepartment(departmentDTO);

        assertNotNull(savedDepartment);
        assertEquals(departmentDTO, savedDepartment);
        verify(departmentRepo).save(department);
        verify(modelMapper).map(departmentDTO, Department.class);
        verify(modelMapper).map(department, DepartmentDTO.class);
    }

    @Test
    public void testUpdateDepartmentById() {
        Department existingDepartment = new Department(1L,"HR","AAAA");
        DepartmentDTO departmentDTO = new DepartmentDTO(1L,"HR","AAAA");

        // Arrange
        when(departmentRepo.findById(1L)).thenReturn(Optional.of(existingDepartment));
        when(modelMapper.map(existingDepartment, DepartmentDTO.class)).thenReturn(departmentDTO);

        // Act
        DepartmentDTO updatedDepartment = departmentService.updateDepartmentById(departmentDTO, 1L);

        // assert
        verify(departmentRepo).findById(1L);
        verify(departmentRepo).save(existingDepartment);
        assertEquals("HR", existingDepartment.getDepartmentName());
        assertEquals("AAAA", existingDepartment.getDepartmentDescription());
        assertEquals(departmentDTO, updatedDepartment);
    }

    @Test
    public void testDeleteDepartmentById() {
        Department existingDepartment = new Department(1L,"HR","AAAA");
        DepartmentDTO departmentDTO = new DepartmentDTO(1L,"HR","AAAA");

        // Arrange
        when(departmentRepo.findById(1L)).thenReturn(Optional.of(existingDepartment));
        when(modelMapper.map(existingDepartment, DepartmentDTO.class)).thenReturn(departmentDTO);

        // Act
        DepartmentDTO deletedDepartment = departmentService.deleteDepartmentById(1L);

        // Assert
        verify(departmentRepo).findById(1L);
        verify(departmentRepo).deleteById(1L);
        assertEquals(departmentDTO, deletedDepartment);
    }
}
