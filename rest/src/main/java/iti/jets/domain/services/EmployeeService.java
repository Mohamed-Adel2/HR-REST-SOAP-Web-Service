package iti.jets.domain.services;

import iti.jets.domain.dtos.Employee.EmployeePostRequest;
import iti.jets.domain.entities.Employee;
import iti.jets.domain.entities.Project;
import iti.jets.domain.entities.VacationRequest;
import iti.jets.persistence.EmployeeRepository;
import iti.jets.persistence.JpaManager;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Set;

public class EmployeeService {

    public Employee findEmployee(int employeeId) {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        Employee employee = employeeRepository.find(entityManager, employeeId);
        entityManager.close();
        return employee;
    }

    public void addEmployee(Employee employee) {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        entityManager.getTransaction().begin();
        employeeRepository.create(entityManager, employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Employee updateEmployee(Employee employee){
        EmployeeRepository employeeRepository = new EmployeeRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        entityManager.getTransaction().begin();
        Employee updatedEmployee = employeeRepository.update(entityManager, employee);
        entityManager.getTransaction().commit();
        entityManager.close();
        return updatedEmployee;
    }

    public void deleteEmployee(int employeeId) {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        entityManager.getTransaction().begin();
        employeeRepository.delete(entityManager, employeeId);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Set<Project> getEmployeeProjects(int employeeId) {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        Employee employee = employeeRepository.find(entityManager, employeeId);
        Set<Project> ret = null;
        if (employee != null) {
            ret = employee.getProjects();
        }
        entityManager.close();
        return ret;
    }

    public Set<VacationRequest> getEmployeeVacationRequests(int employeeId) {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        Employee employee = employeeRepository.find(entityManager, employeeId);
        Set<VacationRequest> ret = null;
        if (employee != null) {
            ret = employee.getVacationRequests();
        }
        entityManager.close();
        return ret;
    }
}
