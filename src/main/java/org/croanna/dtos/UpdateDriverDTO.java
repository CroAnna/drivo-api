package org.croanna.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateDriverDTO {
    private String name;
    private Boolean passedPracticalTest;
    private Boolean passedTheoryTest;
    private Integer hoursDriven;
}
