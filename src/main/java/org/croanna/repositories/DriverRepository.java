package org.croanna.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.croanna.dtos.PersonPerCategoryResponseDTO;
import org.croanna.models.Driver;

import java.util.List;

@ApplicationScoped
public class DriverRepository {
    @PersistenceContext
    EntityManager em;

    public List<Driver> findAll(int page, int size) {
        return em.createQuery("SELECT d FROM Driver d", Driver.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    public Long count() {
        return em.createQuery("SELECT COUNT(d) FROM Driver d", Long.class)
                .getSingleResult();
    }

    public Driver save(Driver driver) {
        em.persist(driver);
        return driver;
    }

    public Driver findById(Long id) {
        return em.createQuery("SELECT d FROM Driver d WHERE d.id = :id", Driver.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Driver update(Driver driver) {
        em.merge(driver);
        return driver;
    }

    public void delete(Long id) {
        em.createQuery("DELETE FROM Driver d WHERE d.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public List<PersonPerCategoryResponseDTO> getActiveDriversPerCategory() {
        return em.createQuery("SELECT new org.croanna.dtos.PersonPerCategoryResponseDTO(c.title, COUNT(d.id)) " +
                        "FROM Driver d " +
                        "JOIN d.category c " +
                        "WHERE d.passedPracticalTest = false " +
                        "GROUP BY c.title " +
                        "ORDER BY c.title ASC", PersonPerCategoryResponseDTO.class)
                .getResultList();
    }

}
