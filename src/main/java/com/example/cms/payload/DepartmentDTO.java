package com.example.cms.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private Long id;

    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 20, message = "Department name between 2 and 20")
    private String departmentName;

    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 200, message = "Department description between 2 and 200")
    private String departmentDescription;
}
