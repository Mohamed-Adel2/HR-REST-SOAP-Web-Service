package iti.jets.persistence;

import iti.jets.domain.entities.WorksOn;

public class WorksOnRepository extends AbstractRepository<WorksOn>{
    public WorksOnRepository() {
        super(WorksOn.class);
    }
}
