package com.example.cms.repository;

import com.example.cms.entity.Department;
import com.example.cms.entity.Employee;
import com.example.cms.exception.ResourceNotFoundException;
import com.example.cms.payload.DepartmentDTO;
import com.example.cms.payload.DepartmentResponse;
import com.example.cms.payload.EmployeeDTO;
import com.example.cms.respository.DepartmentRepo;
import com.example.cms.service.Implementation.DepartmentServiceImpl;
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
public class DepartmentRepoTest {

    @Mock
    private DepartmentRepo departmentRepo;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Mock
    private ModelMapper modelMapper; // Mock ModelMapper

    @Test
    public void testCreateDepartment() {
        // 创建一个 Department 对象
        Department department = new Department();
        department.setDepartmentName("HR");
        department.setDepartmentDescription("AAAAA");

        // 模拟保存操作 - 设置期望的行为
        when(departmentRepo.save(department)).thenReturn(department);

        // 调用保存方法
        Department savedDepartment = departmentRepo.save(department);

        // 验证保存的部门
        assertNotNull(savedDepartment); // 确保返回的对象不为null
        assertEquals("HR", department.getDepartmentName());
        assertEquals("AAAAA", department.getDepartmentDescription());
        verify(departmentRepo, times(1)).save(department); // 验证 save 方法被调用
    }

    @Test
    public void testGetAllDepartments() {
        // 创建 Department 对象列表
        Department department1 = new Department(1L,"HR","AA");
        Department department2 = new Department(2L, "IT", "BB");

        // 模拟 findAll 方法的返回值 - 设置期望的行为
        when(departmentRepo.findAll()).thenReturn(Arrays.asList(department1, department2));

        // 模拟 ModelMapper 的行为 - 设置期望的行为
        when(modelMapper.map(department1, DepartmentDTO.class)).thenReturn(new DepartmentDTO(1L,"HR","AA"));
        when(modelMapper.map(department2, DepartmentDTO.class)).thenReturn(new DepartmentDTO(2L, "IT", "BB"));

        // 调用 getAllDepartments 方法
        DepartmentResponse departmentResponse = departmentService.getAllDepartments();

        // 验证返回结果
        List<DepartmentDTO> departmentDTOS = departmentResponse.getDepartmentDTOList();
        assertEquals(2, departmentDTOS.size());
        assertEquals("HR", departmentDTOS.get(0).getDepartmentName());
        assertEquals("AA", departmentDTOS.get(0).getDepartmentDescription());
        assertEquals("IT", departmentDTOS.get(1).getDepartmentName());
        assertEquals("BB", departmentDTOS.get(1).getDepartmentDescription());
    }

    @Test
    public void testGetDepartmentById() {
        // 创建 Department 对象列表
        Department department = new Department(3L, "HR", "AABB");

        // 模拟 findById 方法的返回值
        when(departmentRepo.findById(3L)).thenReturn(Optional.of(department));

        // 模拟 ModelMapper 的行为
        when(modelMapper.map(department, DepartmentDTO.class)).thenReturn(new DepartmentDTO(3L, "HR", "AABB"));

        // 调用服务方法
        DepartmentDTO departmentDTO = departmentService.getDepartmentById(3L);

        // 验证返回结果
        assertEquals(3L, departmentDTO.getId());
        assertEquals("HR", departmentDTO.getDepartmentName());
        assertEquals("AABB", departmentDTO.getDepartmentDescription());
    }

    @Test
    public void testUpdateDepartmentById() {
        // 创建原始 Department 对象
        Department existingDepartment = new Department(1L, "HR", "AAA");

        // 创建 DTO 对象
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentName("IT");
        departmentDTO.setDepartmentDescription("BBB");

        // 模拟 findById 方法的返回值
        when(departmentRepo.findById(1L)).thenReturn(Optional.of(existingDepartment));

        // 模拟 ModelMapper 的行为
        when(modelMapper.map(existingDepartment, DepartmentDTO.class)).thenReturn(departmentDTO);

        // 调用 updateEmployeeById 方法
        DepartmentDTO updatedDepartmentDTO = departmentService.updateDepartmentById(departmentDTO, 1L);

        // 验证更新后的值
        assertEquals("IT", existingDepartment.getDepartmentName());
        assertEquals("BBB", existingDepartment.getDepartmentDescription());

        // 验证返回的 DTO
        assertEquals("IT", updatedDepartmentDTO.getDepartmentName());
        assertEquals("BBB", updatedDepartmentDTO.getDepartmentDescription());
    }

    @Test
    public void testDeleteDepartmentById() {
        // 模拟 findById 方法的返回值为 Optional.empty()
        when(departmentRepo.findById(1L)).thenReturn(Optional.empty());

        // 验证抛出 ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> {
            departmentService.deleteDepartmentById(1L);
        });
    }
}
