package org.croanna.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.croanna.enums.RoleType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateEmployeeDTO {
    private String name;
    private String phone;
    private String availability;
    private String password;
    private String username;
    private RoleType role;
}
