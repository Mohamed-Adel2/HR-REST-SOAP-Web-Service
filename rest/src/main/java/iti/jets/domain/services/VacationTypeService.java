package iti.jets.domain.services;

import iti.jets.domain.entities.VacationType;
import iti.jets.persistence.JpaManager;
import iti.jets.persistence.VacationTypeRepository;
import jakarta.persistence.EntityManager;

public class VacationTypeService {
    public void createVacationType(VacationType vacationType) {
        VacationTypeRepository vacationTypeRepository = new VacationTypeRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        entityManager.getTransaction().begin();
        vacationTypeRepository.create(entityManager, vacationType);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public VacationType findVacationType(int vacationTypeId) {
        VacationTypeRepository vacationTypeRepository = new VacationTypeRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        VacationType vacationType = vacationTypeRepository.find(entityManager, vacationTypeId);
        entityManager.close();
        return vacationType;
    }

    public VacationType updateVacationType(VacationType vacationType) {
        VacationTypeRepository vacationTypeRepository = new VacationTypeRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            VacationType updatedVacationType = vacationTypeRepository.update(entityManager, vacationType);
            entityManager.getTransaction().commit();
            entityManager.close();
            return updatedVacationType;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            entityManager.close();
            throw new RuntimeException("VacationType with id " + vacationType.getId() + " is not updated");
        }
    }

    public void deleteVacationType(int vacationTypeId) {
        VacationTypeRepository vacationTypeRepository = new VacationTypeRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        entityManager.getTransaction().begin();
        vacationTypeRepository.delete(entityManager, vacationTypeId);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
