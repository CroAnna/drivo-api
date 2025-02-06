package org.croanna.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    
    private LocalDateTime start_time;

    private LocalDateTime end_time;

    private String location;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

}
