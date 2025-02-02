package org.croanna.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "statuses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Status extends PanacheEntity {

    public String title;
}
