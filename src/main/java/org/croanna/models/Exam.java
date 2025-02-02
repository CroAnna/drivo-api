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
@Table(name = "exams")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Exam extends PanacheEntity {

    public LocalDateTime date;

    public String type;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    public Driver driver;

}
