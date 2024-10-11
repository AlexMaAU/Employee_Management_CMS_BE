package com.example.cms.repository;

import com.example.cms.entity.Employee;
import com.example.cms.exception.ResourceNotFoundException;
import com.example.cms.payload.EmployeeDTO;
import com.example.cms.payload.EmployeeResponse;
import com.example.cms.respository.EmployeeRepo;
import com.example.cms.service.Implementation.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeRepoTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeServiceImpl employeeService;  // 使用具体实现类

    @Mock
    private ModelMapper modelMapper; // Mock ModelMapper

    @Test
    public void testCreateEmployee() {
        // 创建一个 Employee 对象
        Employee employee = new Employee();
        employee.setFirstName("Alex");
        employee.setLastName("Ma");
        employee.setEmail("alex@test.com");

        // 模拟保存操作 - 设置期望的行为
        // employeeRepo.save(employee) 以后 应该返回 employee
        when(employeeRepo.save(employee)).thenReturn(employee);

        // 调用保存方法
        Employee savedEmployee = employeeRepo.save(employee);

        // 验证保存的员工
        assertNotNull(savedEmployee); // 确保返回的对象不为null
        assertEquals("Alex", savedEmployee.getFirstName()); // 验证名字
        assertEquals("Ma", savedEmployee.getLastName());
        assertEquals("alex@test.com", savedEmployee.getEmail());
        verify(employeeRepo, times(1)).save(employee); // 验证 save 方法被调用
    }

    @Test
    public void testGetAllEmployees() {
        // 创建 Employee 对象列表
        Employee employee1 = new Employee(1L,"Alex","Ma","test1@email.com");
        Employee employee2 = new Employee(2L, "Bob", "Jane", "test2@email.com");

        // 模拟 findAll 方法的返回值 - 设置期望的行为
        when(employeeRepo.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        // 模拟 ModelMapper 的行为 - 设置期望的行为
        when(modelMapper.map(employee1, EmployeeDTO.class)).thenReturn(new EmployeeDTO(1L,"Alex","Ma","test1@email.com"));
        when(modelMapper.map(employee2, EmployeeDTO.class)).thenReturn(new EmployeeDTO(2L, "Bob", "Jane", "test2@email.com"));

        // 调用 getAllEmployees 方法
        EmployeeResponse employeeResponse = employeeService.getAllEmployees(); // 使用 EmployeeResponse

        // 验证返回结果
        List<EmployeeDTO> employeeDTOS = employeeResponse.getEmployeeDTOList();
        assertEquals(2, employeeDTOS.size());
        assertEquals("Alex", employeeDTOS.get(0).getFirstName());
        assertEquals("Ma", employeeDTOS.get(0).getLastName());
        assertEquals("test1@email.com", employeeDTOS.get(0).getEmail());
        assertEquals("Bob", employeeDTOS.get(1).getFirstName());
        assertEquals("Jane", employeeDTOS.get(1).getLastName());
        assertEquals("test2@email.com", employeeDTOS.get(1).getEmail());
    }

    @Test
    public void testGetEmployeeById() {
        // 创建 Employee 对象列表
        Employee employee = new Employee(3L, "Joe", "Shin", "test3@email.com");

        // 模拟 findById 方法的返回值
        when(employeeRepo.findById(3L)).thenReturn(Optional.of(employee));

        // 模拟 ModelMapper 的行为
        when(modelMapper.map(employee, EmployeeDTO.class)).thenReturn(new EmployeeDTO(3L, "Joe", "Shin", "test3@email.com"));

        // 调用服务方法
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(3L);

        // 验证返回结果
        assertEquals(3L, employeeDTO.getId());
        assertEquals("Joe", employeeDTO.getFirstName());
        assertEquals("Shin", employeeDTO.getLastName());
        assertEquals("test3@email.com", employeeDTO.getEmail());
    }

    @Test
    public void testUpdateEmployeeById() {
        // 创建原始 Employee 对象
        Employee existingEmployee = new Employee(1L, "Ada", "Mokcu", "test4@email.com");

        // 创建 DTO 对象
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("Uda");
        employeeDTO.setLastName("Andor");
        employeeDTO.setEmail("test77@email.com");

        // 模拟 findById 方法的返回值
        when(employeeRepo.findById(1L)).thenReturn(Optional.of(existingEmployee));

        // 模拟 ModelMapper 的行为
        when(modelMapper.map(existingEmployee, EmployeeDTO.class)).thenReturn(employeeDTO);

        // 调用 updateEmployeeById 方法
        EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployeeById(employeeDTO, 1L);

        // 验证更新后的值
        assertEquals("Uda", existingEmployee.getFirstName());
        assertEquals("Andor", existingEmployee.getLastName());
        assertEquals("test77@email.com", existingEmployee.getEmail());

        // 验证返回的 DTO
        assertEquals("Uda", updatedEmployeeDTO.getFirstName());
        assertEquals("Andor", updatedEmployeeDTO.getLastName());
        assertEquals("test77@email.com", updatedEmployeeDTO.getEmail());
    }

    @Test
    public void testDeleteEmployeeById() {
        // 模拟 findById 方法的返回值为 Optional.empty()
        when(employeeRepo.findById(1L)).thenReturn(Optional.empty());

        // 验证抛出 ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.deleteEmployeeById(1L);
        });
    }
}
