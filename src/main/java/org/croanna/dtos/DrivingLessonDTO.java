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
public class DrivingLessonDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private String comment;
    private StatusType status;

    @NotNull(message = "Driver ID cannot be null!")
    private Long driverId;

    @NotNull(message = "Instructor ID cannot be null!")
    private Long instructorId;
}
