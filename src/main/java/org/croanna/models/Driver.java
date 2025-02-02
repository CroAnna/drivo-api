package org.croanna.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
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

    @Column(name = "passed_practical_test")
    public boolean passedPracticalTest;

    @Column(name = "passed_theory_test")
    public boolean passedTheoryTest;

    @Column(name = "hours_driven")
    public Integer hoursDriven;

    public String phone;

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category category;
}
