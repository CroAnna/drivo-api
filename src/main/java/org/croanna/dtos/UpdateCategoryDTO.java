package org.croanna.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCategoryDTO {
    private String title;
    private String description;
    private Integer requiredHours;
    private Integer minimalAge;
}
