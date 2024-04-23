package iti.jets.domain.services;

import iti.jets.domain.entities.Department;
import iti.jets.domain.entities.Employee;
import iti.jets.domain.entities.Project;
import iti.jets.domain.entities.VacationRequest;
import iti.jets.domain.exceptions.models.DataNotFoundException;
import iti.jets.domain.exceptions.models.DataNotModifiedException;
import iti.jets.persistence.EmployeeRepository;
import iti.jets.persistence.JpaManager;
import jakarta.persistence.EntityManager;

import java.util.Set;

public class EmployeeService {

    public Employee findEmployee(int employeeId) {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        Employee employee = employeeRepository.find(entityManager, employeeId);
        Department dep = employee.getManagedDepartment();
        entityManager.close();
        if (dep == null)
            throw new DataNotFoundException("Employee with id " + employeeId + " is not assigned to a department");
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

    public Employee updateEmployee(Employee employee) {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Employee updatedEmployee = employeeRepository.update(entityManager, employee);
            entityManager.getTransaction().commit();
            entityManager.close();
            return updatedEmployee;
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
            entityManager.close();
            throw new DataNotModifiedException("Employee with id " + employee.getId() + " is not updated");
        }

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
            System.out.println(ret.size());
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
            ret.size();
        }
        entityManager.close();
        return ret;
    }
}
