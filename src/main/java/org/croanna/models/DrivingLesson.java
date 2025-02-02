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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public LocalDateTime start;

    public LocalDateTime end;

    public String location;

    public String comment;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    public Integer driverId;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    public Integer instructorId;

    @ManyToOne
    @JoinColumn(name = "status_id")
    public Status status;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    public Driver driver;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    public Instructor instructor;
}
