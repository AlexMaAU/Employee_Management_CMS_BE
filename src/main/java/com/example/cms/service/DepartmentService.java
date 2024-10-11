package com.example.cms.service;

import com.example.cms.payload.DepartmentDTO;
import com.example.cms.payload.DepartmentResponse;
import jakarta.validation.Valid;

public interface DepartmentService {
    DepartmentResponse getAllDepartments();

    DepartmentDTO getDepartmentById(Long id);

    DepartmentDTO createDepartment(@Valid DepartmentDTO departmentDTO);

    DepartmentDTO updateDepartmentById(@Valid DepartmentDTO departmentDTO, Long id);

    DepartmentDTO deleteDepartmentById(Long id);
}
