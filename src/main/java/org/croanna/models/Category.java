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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String title;

    public String description;

    @Column(name = "required_hours")
    public Integer requiredHours;

    @Column(name = "minimal_age")
    public Integer minimalAge;

    @ManyToMany(mappedBy = "categories")
    private Set<Instructor> instructors;
}
