package org.croanna.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
public class Category extends PanacheEntity {
   
    public String title;

    public String description;

    @Column(name = "required_hours")
    public Integer requiredHours;

    @Column(name = "minimal_age")
    public Integer minimalAge;

    @ManyToMany(mappedBy = "categories")
    private Set<Instructor> instructors;
}
