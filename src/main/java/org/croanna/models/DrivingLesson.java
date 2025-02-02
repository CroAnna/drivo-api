package org.croanna.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
public class DrivingLesson extends PanacheEntity {

    public LocalDateTime start_time;

    public LocalDateTime end_time;

    public String location;

    public String comment;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    public Driver driver;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    public Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "status_id")
    public Status status;

}
