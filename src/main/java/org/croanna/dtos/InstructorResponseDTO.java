package org.croanna.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.croanna.enums.RoleType;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class InstructorResponseDTO extends EmployeeResponseDTO {
    private String availability;
    private Set<String> categories;

    public InstructorResponseDTO(Long id, String name, String phone, String username,
                                 RoleType role, String availability, Set<String> categories) {
        super(id, name, phone, username, role);
        this.availability = availability;
        this.categories = categories;
    }
}
