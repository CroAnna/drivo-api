package org.croanna.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCategoryDTO {
    private Long id;

    @NotNull(message = "Title cannot be null!")
    @NotBlank(message = "Title cannot be blank!")
    private String title;

    @NotNull(message = "Description cannot be null!")
    @NotBlank(message = "Description cannot be blank!")
    private String description;

    @NotNull(message = "Required hours cannot be null!")
    private Integer requiredHours;

    @NotNull(message = "Minimal age cannot be null!")
    private Integer minimalAge;
}
