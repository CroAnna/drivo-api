package org.croanna.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "drivers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Driver extends PanacheEntity {

    public String name;

    @Column(name = "hours_driven")
    public int hoursDriven;

    @Column(name = "passed_driving_test")
    public boolean passedDrivingTest;
}
