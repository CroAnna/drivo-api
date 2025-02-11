package org.croanna.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.croanna.models.RequestLog;

@ApplicationScoped
public class RequestLogRepository {
    @PersistenceContext
    EntityManager em;

    public void save(RequestLog log) {
        em.persist(log);
    }
}
