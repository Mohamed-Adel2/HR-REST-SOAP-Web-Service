package iti.jets.persistence;

import iti.jets.domain.entities.VacationType;

public class VacationTypeRepository extends AbstractRepository<VacationType> {
    public VacationTypeRepository() {
        super(VacationType.class);
    }
}
