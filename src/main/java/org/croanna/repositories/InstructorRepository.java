package org.croanna.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.croanna.models.Instructor;

import java.util.List;

@ApplicationScoped
public class InstructorRepository {
    @PersistenceContext
    EntityManager em;

    public List<Instructor> findAll(int page, int size) {
        return em.createQuery("SELECT i FROM Instructor i", Instructor.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    public Long count() {
        return em.createQuery("SELECT COUNT(i) FROM Instructor i", Long.class)
                .getSingleResult();
    }
}
