package org.croanna.models;

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
public class Driver {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(name = "passed_practical_test")
    private boolean passedPracticalTest;

    @Column(name = "passed_theory_test")
    private boolean passedTheoryTest;

    @Column(name = "hours_driven")
    private Integer hoursDriven;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
