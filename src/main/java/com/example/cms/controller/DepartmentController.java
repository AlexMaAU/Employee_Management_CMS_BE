package com.example.cms.controller;

import com.example.cms.payload.DepartmentDTO;
import com.example.cms.payload.DepartmentResponse;
import com.example.cms.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public ResponseEntity<DepartmentResponse> getAllDepartments() {
        DepartmentResponse departmentResponse = departmentService.getAllDepartments();

        return new ResponseEntity<>(departmentResponse, HttpStatus.OK);
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        DepartmentDTO departmentDTO = departmentService.getDepartmentById(id);

        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }

    @PostMapping("/departments")
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO savedDepartment = departmentService.createDepartment(departmentDTO);

        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartmentById(@Valid @RequestBody DepartmentDTO departmentDTO, @PathVariable Long id) {
        DepartmentDTO updateDepartmentDTO = departmentService.updateDepartmentById(departmentDTO, id);

        return new ResponseEntity<>(updateDepartmentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<DepartmentDTO> deleteDepartmentById(@PathVariable Long id) {
        DepartmentDTO departmentDTO = departmentService.deleteDepartmentById(id);

        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }
}
