package org.croanna.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.croanna.enums.RoleType;

import java.util.Set;

@Entity
@Table(name = "instructors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Instructor extends Employee {
    @Column(nullable = false)
    private String availability;

    @ManyToMany
    @JoinTable(
            name = "instructor_categories",
            joinColumns = @JoinColumn(name = "instructor_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    public Instructor(Long id, String username, String password, String name, String phone, String availability, Set<Category> categories) {
        super(id, username, password, name, phone, RoleType.INSTRUCTOR);
        this.availability = availability;
        this.categories = categories;
    }
}
