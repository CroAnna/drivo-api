package org.croanna.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.croanna.enums.StatusType;

import java.time.LocalDateTime;

@Entity
@Table(name = "driving_lessons")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DrivingLesson {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String location;

    private String comment;

    private StatusType status;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

}
