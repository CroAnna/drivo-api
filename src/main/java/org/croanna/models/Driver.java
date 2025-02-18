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

    @Column(nullable = false)
    private String name;

    @Column(name = "passed_practical_test")
    private Boolean passedPracticalTest = false;

    @Column(name = "passed_theory_test")
    private Boolean passedTheoryTest = false;

    @Column(name = "hours_driven")
    private Integer hoursDriven;

    @Column(nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @PrePersist
    public void prePersist() {
        if (this.hoursDriven == null) {
            this.hoursDriven = 0;
        }
    }

}
