package org.croanna.dtos;

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
public class ExamResponseDTO {
    private Long id;
    private LocalDateTime date;
    private StatusType status;
    private ExamType type;
    private Long driverId;
    private Long employeeId;
}
