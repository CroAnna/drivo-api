package org.croanna.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.croanna.models.Category;

import java.util.List;

@ApplicationScoped
public class CategoryRepository {
    @PersistenceContext
    EntityManager em;

    public List<Category> findAll(int page, int size) {
        return em.createQuery("SELECT c FROM Category c", Category.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    public Long count() {
        return em.createQuery("SELECT COUNT(c) FROM Category c", Long.class)
                .getSingleResult();
    }
}
