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
public class CreateDriverDTO {
    private Long id;

    @NotNull(message = "Name cannot be null!")
    @NotBlank(message = "Name cannot be blank!")
    private String name;

    private Boolean passedPracticalTest;

    private Boolean passedTheoryTest;

    private Integer hoursDriven;

    @NotNull(message = "Phone cannot be null!")
    @NotBlank(message = "Phone cannot be blank!")
    private String phone;

    @NotNull(message = "Category ID cannot be null!")
    private Long categoryId;
}
