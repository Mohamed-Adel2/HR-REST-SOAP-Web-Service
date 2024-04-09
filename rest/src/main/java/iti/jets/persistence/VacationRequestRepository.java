package iti.jets.persistence;

import iti.jets.domain.entities.VacationRequest;

public class VacationRequestRepository extends AbstractRepository<VacationRequest>{
    public VacationRequestRepository() {
        super(VacationRequest.class);
    }
}
