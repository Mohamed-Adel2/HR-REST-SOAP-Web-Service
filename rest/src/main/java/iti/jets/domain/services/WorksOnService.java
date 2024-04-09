package iti.jets.domain.services;

import iti.jets.domain.entities.WorksOn;
import iti.jets.domain.entities.WorksOnId;
import iti.jets.persistence.JpaManager;
import iti.jets.persistence.WorksOnRepository;
import jakarta.persistence.EntityManager;

public class WorksOnService {
    public void addEmployeeToProject(WorksOn worksOn) {
        WorksOnRepository worksOnRepository = new WorksOnRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        entityManager.getTransaction().begin();
        worksOnRepository.create(entityManager, worksOn);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public WorksOn getEmployeeFromProject(WorksOnId worksOnId) {
        WorksOnRepository worksOnRepository = new WorksOnRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        WorksOn worksOn = worksOnRepository.find(entityManager, worksOnId);
        entityManager.close();
        return worksOn;
    }

    public void removeEmployeeFromProject(WorksOnId worksOnId) {
        WorksOnRepository worksOnRepository = new WorksOnRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        entityManager.getTransaction().begin();
        worksOnRepository.delete(entityManager, worksOnId);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
