package com.example.cms.payload;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentDTOTest {

    @Test
    void testDepartmentDTOConstructorAndGetter() {
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "HR", "AAA");

        assertEquals(1L, departmentDTO.getId());
        assertEquals("HR", departmentDTO.getDepartmentName());
        assertEquals("AAA", departmentDTO.getDepartmentDescription());
    }

    @Test
    void testDepartmentDTOSetter() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(2L);
        departmentDTO.setDepartmentName("IT");
        departmentDTO.setDepartmentDescription("AABB");

        assertEquals(2L, departmentDTO.getId());
        assertEquals("IT", departmentDTO.getDepartmentName());
        assertEquals("AABB", departmentDTO.getDepartmentDescription());
    }
}
