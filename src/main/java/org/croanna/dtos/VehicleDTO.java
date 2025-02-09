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
public class VehicleDTO {
    private Long id;

    @NotNull(message = "Licence plate cannot be null!")
    @NotBlank(message = "Licence plate cannot be blank!")
    private String licencePlate;

    @NotNull(message = "Model cannot be null!")
    @NotBlank(message = "Model cannot be blank!")
    private String model;

    @NotNull(message = "Year cannot be null!")
    private Integer year;
}
