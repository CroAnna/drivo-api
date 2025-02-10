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
}
