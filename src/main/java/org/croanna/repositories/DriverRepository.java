package org.croanna.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.croanna.models.Driver;

@ApplicationScoped
public class DriverRepository implements PanacheRepository<Driver> {

}
