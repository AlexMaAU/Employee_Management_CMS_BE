package com.example.cms.payload;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeResponseTest {

    @Test
    void testEmployeeResponseConstructorAndGetter() {
        EmployeeResponse employeeResponse = new EmployeeResponse(
                Arrays.asList(new EmployeeDTO(1L,"Alex","Ma","test@email.com"), new EmployeeDTO(2L,"Bob","Eddy","test2@email.com"))
        );

        assertEquals(1L, employeeResponse.getEmployeeDTOList().get(0).getId());
        assertEquals(2L, employeeResponse.getEmployeeDTOList().get(1).getId());
        assertEquals("Alex", employeeResponse.getEmployeeDTOList().get(0).getFirstName());
        assertEquals("Ma", employeeResponse.getEmployeeDTOList().get(0).getLastName());
        assertEquals("test@email.com", employeeResponse.getEmployeeDTOList().get(0).getEmail());
        assertEquals("Bob", employeeResponse.getEmployeeDTOList().get(1).getFirstName());
        assertEquals("Eddy", employeeResponse.getEmployeeDTOList().get(1).getLastName());
        assertEquals("test2@email.com", employeeResponse.getEmployeeDTOList().get(1).getEmail());
    }

    @Test
    void testEmployeeResponseConstructorSetter() {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeDTOList(Arrays.asList(new EmployeeDTO()));

        assertEquals(1, employeeResponse.getEmployeeDTOList().size());
    }
}
