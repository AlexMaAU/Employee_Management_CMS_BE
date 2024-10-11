package com.example.cms.payload;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeDTOTest {

    @Test
    void testEmployeeDTOConstructorAndGetter() {
        EmployeeDTO employeeDTO = new EmployeeDTO(1L, "Alex", "Ma", "test@email.com");

        assertEquals(1L, employeeDTO.getId());
        assertEquals("Alex", employeeDTO.getFirstName());
        assertEquals("Ma", employeeDTO.getLastName());
        assertEquals("test@email.com", employeeDTO.getEmail());
    }

    @Test
    void testEmployeeDTOSetter() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(2L);
        departmentDTO.setDepartmentName("IT");
        departmentDTO.setDepartmentDescription("AABB");

        assertEquals(2L, departmentDTO.getId());
        assertEquals("IT", departmentDTO.getDepartmentName());
        assertEquals("AABB", departmentDTO.getDepartmentDescription());
    }
}
