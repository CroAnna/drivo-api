package org.croanna.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "exams")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public LocalDateTime date;

    public String type;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    public Driver driver;

}
