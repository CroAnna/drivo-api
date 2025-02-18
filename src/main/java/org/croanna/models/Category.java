package org.croanna.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "required_hours", nullable = false)
    private Integer requiredHours;

    @Column(name = "minimal_age", nullable = false)
    private Integer minimalAge;

    @ManyToMany(mappedBy = "categories")
    private Set<Instructor> instructors;
}
