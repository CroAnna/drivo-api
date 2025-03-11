package org.croanna.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.croanna.enums.RoleType;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateEmployeeDTO {
    private Long id;

    @NotNull(message = "Name cannot be null!")
    @NotBlank(message = "Name cannot be blank!")
    private String name;

    @NotNull(message = "Phone cannot be null!")
    @NotBlank(message = "Phone cannot be blank!")
    private String phone;

    @NotNull(message = "Username cannot be null!")
    @NotBlank(message = "Username cannot be blank!")
    private String username;

    @NotNull(message = "Password cannot be null!")
    @NotBlank(message = "Password cannot be blank!")
    private String password;

    @NotNull(message = "Role cannot be null!")
    private RoleType role;

    private String availability;

    private Set<String> categories;
}

