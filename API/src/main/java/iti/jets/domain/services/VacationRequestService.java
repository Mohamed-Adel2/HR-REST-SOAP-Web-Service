package iti.jets.domain.services;

import iti.jets.domain.entities.VacationRequest;
import iti.jets.persistence.JpaManager;
import iti.jets.persistence.VacationRequestRepository;
import jakarta.persistence.EntityManager;

public class VacationRequestService {
    public void createVacationRequest(VacationRequest vacationRequest) {
        VacationRequestRepository vacationRequestRepository = new VacationRequestRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        entityManager.getTransaction().begin();
        vacationRequestRepository.create(entityManager, vacationRequest);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public VacationRequest findVacationRequest(int vacationRequestId) {
        VacationRequestRepository vacationRequestRepository = new VacationRequestRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        VacationRequest vacationRequest = vacationRequestRepository.find(entityManager, vacationRequestId);
        entityManager.close();
        return vacationRequest;
    }

    public VacationRequest updateVacationRequest(VacationRequest vacationRequest) {
        VacationRequestRepository vacationRequestRepository = new VacationRequestRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            VacationRequest updatedVacationRequest = vacationRequestRepository.update(entityManager, vacationRequest);
            entityManager.getTransaction().commit();
            entityManager.close();
            return updatedVacationRequest;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            entityManager.close();
            throw new RuntimeException("VacationRequest with id " + vacationRequest.getId() + " is not updated");
        }
    }

    public void deleteVacationRequest(int vacationRequestId) {
        VacationRequestRepository vacationRequestRepository = new VacationRequestRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        entityManager.getTransaction().begin();
        vacationRequestRepository.delete(entityManager, vacationRequestId);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
