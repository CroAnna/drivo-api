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

    public Instructor save(Instructor instructor) {
        em.persist(instructor);
        return instructor;
    }

    public Instructor findById(Long id) {
        return em.createQuery("SELECT i FROM Instructor i WHERE i.id = :id", Instructor.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Instructor update(Instructor instructor) {
        em.merge(instructor);
        return instructor;
    }

    public void delete(Long id) {
        em.createQuery("DELETE FROM Instructor i WHERE i.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
