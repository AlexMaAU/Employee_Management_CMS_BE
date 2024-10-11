package com.example.cms.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentTest {
    @Test
    void testDepartmentConstructorAndGetter() {
        Department department = new Department(1L, "HR", "AAAA");

        assertEquals(1L, department.getId());
        assertEquals("HR", department.getDepartmentName());
        assertEquals("AAAA", department.getDepartmentDescription());
    }

    @Test
    void testDepartmentSetter() {
        Department department = new Department();

        department.setId(2L);
        department.setDepartmentName("IT");
        department.setDepartmentDescription("BBBB");

        assertEquals(2L, department.getId());
        assertEquals("IT", department.getDepartmentName());
        assertEquals("BBBB", department.getDepartmentDescription());
    }
}
