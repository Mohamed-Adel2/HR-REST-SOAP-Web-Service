package iti.jets.persistence;

import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepository<T> {
    private final Class<T> entityClass;

    public AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(EntityManager em, T entity) {
        em.persist(entity);
    }

    public T find(EntityManager em, Object id) {
        return em.find(entityClass, id);
    }

    public T update(EntityManager em, T entity) {
        em.merge(entity);
        return entity;
    }

    public void delete(EntityManager em, Object id) {
        T entity = find(em, id);
        if (entity != null) {
            em.remove(entity);
        }
    }
}
