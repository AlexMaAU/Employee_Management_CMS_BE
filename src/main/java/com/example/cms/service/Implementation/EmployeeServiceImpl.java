package com.example.cms.service.Implementation;

import com.example.cms.entity.Employee;
import com.example.cms.exception.ResourceNotFoundException;
import com.example.cms.payload.EmployeeDTO;
import com.example.cms.payload.EmployeeResponse;
import com.example.cms.respository.EmployeeRepo;
import com.example.cms.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeResponse getAllEmployees() {
        List<Employee> employees = employeeRepo.findAll();
        List<EmployeeDTO> employeeDTOS = employees.stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).toList();
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeDTOList(employeeDTOS);

        return employeeResponse;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee savedEmployee = employeeRepo.save(employee);

        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO, Long id) {
        Employee existEmployee = employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "employeeId", id));

        if(employeeDTO.getFirstName() != null) {
            existEmployee.setFirstName(employeeDTO.getFirstName());
        }
        if(employeeDTO.getLastName() != null) {
            existEmployee.setLastName(employeeDTO.getLastName());
        }
        if(employeeDTO.getEmail() != null) {
            existEmployee.setEmail(employeeDTO.getEmail());
        }

        employeeRepo.save(existEmployee);

        return modelMapper.map(existEmployee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "employeeId", id));

        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO deleteEmployeeById(Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "employeeId", id));

        employeeRepo.deleteById(id);

        return modelMapper.map(employee, EmployeeDTO.class);
    }

}
