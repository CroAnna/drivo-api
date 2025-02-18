package org.croanna.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DriverResponseDTO {
    private Long id;
    private String name;
    private Boolean passedPracticalTest;
    private Boolean passedTheoryTest;
    private Integer hoursDriven;
    private String phone;
    private Long categoryId;
}
