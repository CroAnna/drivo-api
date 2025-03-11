package org.croanna.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.croanna.enums.ExamType;
import org.croanna.enums.StatusType;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateExamDTO {
    private Long id;

    @NotNull(message = "Date cannot be null!")
    private LocalDateTime date;

    @NotNull(message = "Status cannot be null!")
    private StatusType status;

    @NotNull(message = "Exam type cannot be null!")
    private ExamType type;

    private Long driverId;
    private Long employeeId;
}
