package com.example.cms.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    @Test
    void testEmployeeConstructorAndGetter() {
        Employee employee = new Employee(1L,"Alex","Ma","alex@email.com");

        assertEquals(1L, employee.getId());
        assertEquals("Alex", employee.getFirstName());
        assertEquals("Ma", employee.getLastName());
        assertEquals("alex@email.com", employee.getEmail());
    }

    @Test
    void testEmployeeSetter() {
        Employee employee = new Employee();

        employee.setId(2L);
        employee.setFirstName("Bob");
        employee.setLastName("Dylon");

        assertEquals(2L, employee.getId());
        assertEquals("Bob", employee.getFirstName());
        assertEquals("Dylon", employee.getLastName());
    }
}
