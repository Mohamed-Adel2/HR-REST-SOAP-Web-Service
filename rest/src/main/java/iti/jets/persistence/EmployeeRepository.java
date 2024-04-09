package iti.jets.persistence;

import iti.jets.domain.entities.Employee;

public class EmployeeRepository extends AbstractRepository<Employee> {
    public EmployeeRepository() {
        super(Employee.class);
    }
}
