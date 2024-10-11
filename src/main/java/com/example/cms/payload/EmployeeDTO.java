package com.example.cms.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;

    @NotEmpty
    @NotBlank
    @Size(min = 2, max = 20, message = "First name between 2 and 20")
    private String firstName;

    @NotEmpty
    @NotBlank
    @Size(min = 2, max = 20, message = "Last name between 2 and 20")
    private String lastName;

    @NotEmpty
    @NotBlank
    @Email
    private String email;
}
