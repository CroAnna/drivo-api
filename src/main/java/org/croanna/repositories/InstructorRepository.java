package org.croanna.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.croanna.models.Instructor;

import java.util.List;

@ApplicationScoped
public class InstructorRepository {
    @PersistenceContext
    EntityManager em;

    public List<Instructor> findAll(int page, int size, Long categoryId) {
        String queryStr = "SELECT i FROM Instructor i";

        if (categoryId != null) {
            System.out.println("nije null " + categoryId);
            queryStr += " JOIN i.categories c WHERE c.id = :categoryId";
        }

        TypedQuery<Instructor> query = em.createQuery(queryStr, Instructor.class)
                .setFirstResult(page * size)
                .setMaxResults(size);

        if (categoryId != null) {
            query.setParameter("categoryId", categoryId);
        }

        return query.getResultList();
    }

    public Long count() {
        return em.createQuery("SELECT COUNT(i) FROM Instructor i", Long.class)
                .getSingleResult();
    }
}
