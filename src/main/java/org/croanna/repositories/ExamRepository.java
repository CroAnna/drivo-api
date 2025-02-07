package org.croanna.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.croanna.models.Exam;

import java.util.List;

@ApplicationScoped
public class ExamRepository {
    @PersistenceContext
    EntityManager em;

    public List<Exam> findAll(int page, int size) {
        return em.createQuery("SELECT e FROM Exam e", Exam.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    public Long count() {
        return em.createQuery("SELECT COUNT(e) FROM Exam e", Long.class)
                .getSingleResult();
    }
}
