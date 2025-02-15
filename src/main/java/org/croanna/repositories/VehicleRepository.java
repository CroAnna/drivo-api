package org.croanna.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.croanna.models.Vehicle;

import java.util.List;

@ApplicationScoped
public class VehicleRepository {
    @PersistenceContext
    EntityManager em;

    public List<Vehicle> findAll(int page, int size) {
        return em.createQuery("SELECT v FROM Vehicle v", Vehicle.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    public Long count() {
        return em.createQuery("SELECT COUNT(v) FROM Vehicle v", Long.class)
                .getSingleResult();
    }

    public Vehicle save(Vehicle vehicle) {
        em.persist(vehicle);
        return vehicle;
    }

    public Vehicle update(Vehicle vehicle) {
        em.merge(vehicle);
        return vehicle;
    }

    public Vehicle findById(Long id) {
        return em.find(Vehicle.class, id);
    }

    public void delete(Long id) {
        em.createQuery("DELETE FROM Vehicle v WHERE v.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }


}
