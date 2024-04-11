package iti.jets.persistence;

import iti.jets.domain.exceptions.models.DataNotCreatedException;
import iti.jets.domain.exceptions.models.DataNotFoundException;
import iti.jets.domain.exceptions.models.DataNotModifiedException;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepository<T> {
    private final Class<T> entityClass;

    public AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(EntityManager em, T entity) {
        try {
            em.persist(entity);
        } catch (Exception e) {
            throw new DataNotCreatedException("Failed to create the " + entityClass.getSimpleName());
        }
    }

    public T find(EntityManager em, Object id) {
        T entity = em.find(entityClass, id);
        if (entity == null)
            throw new DataNotFoundException("Failed to find the " + entityClass.getSimpleName() + ", the entity was not found");
        return entity;
    }

    public T update(EntityManager em, T entity) {
        try {
            em.merge(entity);
            return entity;
        }
        catch (Exception e) {
            throw new DataNotModifiedException("Failed to update the " + entityClass.getSimpleName());
        }
    }

    public void delete(EntityManager em, Object id) {
        T entity = find(em, id);
        if (entity != null) {
            em.remove(entity);
        }
        else
            throw new DataNotFoundException("Failed to delete the " + entityClass.getSimpleName() + ", the entity was not found");
    }
}
