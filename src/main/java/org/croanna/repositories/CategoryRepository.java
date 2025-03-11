package org.croanna.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.croanna.models.Category;

import java.util.List;

@ApplicationScoped
public class CategoryRepository {
    @PersistenceContext
    EntityManager em;

    public List<Category> findAll(int page, int size, Long instructorId) {
        String queryStr = "SELECT c FROM Category c";

        if (instructorId != null) {
            queryStr += " JOIN c.instructors i WHERE i.id = :instructorId";
        }

        TypedQuery<Category> query = em.createQuery(queryStr, Category.class)
                .setFirstResult(page * size)
                .setMaxResults(size);

        if (instructorId != null) {
            query.setParameter("instructorId", instructorId);
        }

        return query.getResultList();
    }

    public Long count() {
        return em.createQuery("SELECT COUNT(c) FROM Category c", Long.class)
                .getSingleResult();
    }

    public Category findById(Long id) {
        return em.createQuery("SELECT c FROM Category c WHERE c.id = :id", Category.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Category findByTitle(String title) {
        return em.createQuery("SELECT c FROM Category c WHERE c.title = :title", Category.class)
                .setParameter("title", title)
                .getSingleResult();
    }

    public Category save(Category category) {
        em.persist(category);
        return category;
    }

    public Category update(Category category) {
        em.merge(category);
        return category;
    }

    public void delete(Long id) {
        em.createQuery("DELETE FROM Category c WHERE c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
