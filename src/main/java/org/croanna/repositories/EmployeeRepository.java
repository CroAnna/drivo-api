package org.croanna.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.croanna.models.Employee;

import java.util.List;

@ApplicationScoped
public class EmployeeRepository {
    @PersistenceContext
    EntityManager em;

    public List<Employee> findAll(int page, int size) {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    public Employee findByUsername(String username) {
        return em.createQuery("SELECT e FROM Employee e WHERE e.username = :username", Employee.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public Long count() {
        return em.createQuery("SELECT COUNT(e) FROM Employee e", Long.class)
                .getSingleResult();
    }

    public Employee save(Employee employee) {
        em.persist(employee);
        return employee;
    }

    public Employee findById(Long id) {
        return em.createQuery("SELECT e FROM Employee e WHERE e.id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Employee update(Employee employee) {
        em.merge(employee);
        return employee;
    }

    public void delete(Long id) {
        em.createQuery("DELETE FROM Employee e WHERE e.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
