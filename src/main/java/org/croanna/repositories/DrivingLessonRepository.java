package org.croanna.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.croanna.models.DrivingLesson;

import java.util.List;

@ApplicationScoped
public class DrivingLessonRepository {

    @PersistenceContext
    EntityManager em;

    public List<DrivingLesson> findAll(int page, int size) {
        return em.createQuery("SELECT v FROM DrivingLesson v", DrivingLesson.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    public Long count() {
        return em.createQuery("SELECT COUNT(v) FROM DrivingLesson v", Long.class)
                .getSingleResult();
    }

    public DrivingLesson save(DrivingLesson lesson) {
        em.persist(lesson);
        return lesson;
    }

    public DrivingLesson findById(Long id) {
        return em.createQuery("SELECT d FROM DrivingLesson d WHERE d.id = :id", DrivingLesson.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
    public DrivingLesson update(DrivingLesson lesson) {
        em.merge(lesson);
        return lesson;
    }

    public void delete(Long id) {
        em.createQuery("DELETE FROM DrivingLesson d WHERE d.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
