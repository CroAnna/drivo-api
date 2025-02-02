package org.croanna.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "instructors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Instructor extends PanacheEntity {

    public String name;

    public String phone;

    public String availability;

    @ManyToMany
    @JoinTable(
            name = "instructor_categories",
            joinColumns = @JoinColumn(name = "instructor_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;
}
