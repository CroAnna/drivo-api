package org.croanna.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehicles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vehicle {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "licence_plate", nullable = false)
    private String licencePlate;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer year;
}
