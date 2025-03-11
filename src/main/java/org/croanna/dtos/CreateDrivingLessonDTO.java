package org.croanna.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.croanna.enums.StatusType;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateDrivingLessonDTO {
    private Long id;

    @NotNull(message = "Start time cannot be null!")
    private LocalDateTime startTime;

    @NotNull(message = "End time cannot be null!")
    private LocalDateTime endTime;

    private String location;
    private String comment;

    @NotNull(message = "Status cannot be null!")
    private StatusType status;

    @NotNull(message = "Driver ID cannot be null!")
    private Long driverId;

    @NotNull(message = "Employee ID cannot be null!")
    private Long employeeId;
}
