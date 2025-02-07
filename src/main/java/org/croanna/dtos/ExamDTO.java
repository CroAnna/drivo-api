package org.croanna.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.croanna.enums.ExamType;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExamDTO {
    private Long id;
    private LocalDateTime date;
    private ExamType type;
    private DriverDTO driver;
    private InstructorDTO instructor;

}
