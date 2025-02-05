package org.croanna.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.croanna.models.Driver;

import java.util.List;

@ApplicationScoped
public class DriverRepository implements PanacheRepository<Driver> {
    public List<Driver> findAllWithCategory() {
        return find("FROM Driver d LEFT JOIN FETCH d.category").list();
    }
}
