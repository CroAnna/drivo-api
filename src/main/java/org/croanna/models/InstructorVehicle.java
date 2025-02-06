package org.croanna.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "instructor_vehicle")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InstructorVehicle {
    @Id
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Id
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
