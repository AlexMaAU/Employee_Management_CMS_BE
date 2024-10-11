package com.example.cms.service.Implementation;

import com.example.cms.entity.Department;
import com.example.cms.exception.ResourceNotFoundException;
import com.example.cms.payload.DepartmentDTO;
import com.example.cms.payload.DepartmentResponse;
import com.example.cms.respository.DepartmentRepo;
import com.example.cms.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DepartmentResponse getAllDepartments() {
        List<Department> departments = departmentRepo.findAll();
        List<DepartmentDTO> departmentDTOS = departments.stream().map(department -> modelMapper.map(department, DepartmentDTO.class)).toList();

        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setDepartmentDTOList(departmentDTOS);

        return departmentResponse;
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Department", "departmentId", id));

        return modelMapper.map(department, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = modelMapper.map(departmentDTO, Department.class);
        Department savedDepartment = departmentRepo.save(department);

        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO updateDepartmentById(DepartmentDTO departmentDTO, Long id) {
        Department existDepartment = departmentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Department", "departmentId", id));

        if(departmentDTO.getDepartmentName() != null) {
            existDepartment.setDepartmentName(departmentDTO.getDepartmentName());
        }
        if(departmentDTO.getDepartmentDescription() != null) {
            existDepartment.setDepartmentDescription(departmentDTO.getDepartmentDescription());
        }

        departmentRepo.save(existDepartment);

        return modelMapper.map(existDepartment, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO deleteDepartmentById(Long id) {
        Department department = departmentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Department", "departmentId", id));

        departmentRepo.deleteById(id);

        return modelMapper.map(department, DepartmentDTO.class);
    }

}
