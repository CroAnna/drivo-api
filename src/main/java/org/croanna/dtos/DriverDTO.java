package org.croanna.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DriverDTO {
    private Long id;
    private String name;
    private boolean passedPracticalTest;
    private boolean passedTheoryTest;
    private Integer hoursDriven;
    private String phone;
    private CategoryDTO category;
}
