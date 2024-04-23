package iti.jets.persistence;

import iti.jets.domain.entities.Department;

public class DepartmentRepository extends AbstractRepository<Department>{
    public DepartmentRepository() {
        super(Department.class);
    }
}
