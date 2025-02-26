package org.croanna.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.croanna.models.Employee;

@ApplicationScoped
public class EmployeeRepository {
    @PersistenceContext
    EntityManager em;

    public Employee findByUsername(String username) {
        return em.createQuery("SELECT e FROM Employee e WHERE e.username = :username", Employee.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public Boolean checkUserCredentials(String username, String password) {
        try {
            Employee employee = em.createQuery("SELECT e FROM Employee e WHERE e.username = :username AND e.password = :password", Employee.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

}
