package org.croanna.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.croanna.enums.ExamType;
import org.croanna.enums.StatusType;

import java.time.LocalDateTime;

@Entity
@Table(name = "exams")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Exam {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime date;
    
    private StatusType status;

    @Enumerated(EnumType.STRING)
    private ExamType type;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;


}
