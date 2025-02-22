package org.croanna.dtos;

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
public class UpdateDrivingLessonDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private String comment;
    private StatusType status;
    private Long driverId;
    private Long instructorId;
}
