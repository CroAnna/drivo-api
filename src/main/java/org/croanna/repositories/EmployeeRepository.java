package org.croanna.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.croanna.dtos.PersonPerCategoryResponseDTO;
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

    public List<Employee> findAll(int page, int size, Long categoryId) {
        String queryStr = "SELECT e FROM Employee e";

        if (categoryId != null) {
            queryStr += " JOIN i.categories c WHERE c.id = :categoryId";
        }

        TypedQuery<Employee> query = em.createQuery(queryStr, Employee.class)
                .setFirstResult(page * size)
                .setMaxResults(size);

        if (categoryId != null) {
            query.setParameter("categoryId", categoryId);
        }

        return query.getResultList();
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

    public List<PersonPerCategoryResponseDTO> getInstructorsPerCategory() {
        return em.createQuery("SELECT new org.croanna.dtos.PersonPerCategoryResponseDTO(c.title, COUNT(e.id)) " +
                        "FROM Employee e " +
                        "JOIN e.categories c " +
                        "GROUP BY c.title " +
                        "ORDER BY c.title ASC", PersonPerCategoryResponseDTO.class)
                .getResultList();
    }
}
