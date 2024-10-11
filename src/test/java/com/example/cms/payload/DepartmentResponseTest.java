package com.example.cms.payload;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class DepartmentResponseTest {

    @Test
    void testDepartmentResponseConstructorAndGetter() {
        DepartmentResponse departmentResponse = new DepartmentResponse(
                Arrays.asList(new DepartmentDTO(1L,"HR","AAA"), new DepartmentDTO(2L,"IT","BBB"))
        );

        assertEquals(1L, departmentResponse.getDepartmentDTOList().get(0).getId());
        assertEquals(2L, departmentResponse.getDepartmentDTOList().get(1).getId());
        assertEquals("HR", departmentResponse.getDepartmentDTOList().get(0).getDepartmentName());
        assertEquals("AAA", departmentResponse.getDepartmentDTOList().get(0).getDepartmentDescription());
        assertEquals("IT", departmentResponse.getDepartmentDTOList().get(1).getDepartmentName());
        assertEquals("BBB", departmentResponse.getDepartmentDTOList().get(1).getDepartmentDescription());
    }

    @Test
    void testDepartmentResponseConstructorSetter() {
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setDepartmentDTOList(Arrays.asList(new DepartmentDTO()));

        assertEquals(1, departmentResponse.getDepartmentDTOList().size());
    }
}
