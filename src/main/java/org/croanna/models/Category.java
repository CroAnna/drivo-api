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

    private String title;

    private String description;

    @Column(name = "required_hours")
    private Integer requiredHours;

    @Column(name = "minimal_age")
    private Integer minimalAge;

    @ManyToMany(mappedBy = "categories")
    private Set<Instructor> instructors;
}
