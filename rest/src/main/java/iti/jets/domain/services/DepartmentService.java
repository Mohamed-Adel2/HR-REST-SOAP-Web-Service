package iti.jets.domain.services;

import iti.jets.domain.entities.Department;
import iti.jets.domain.entities.Employee;
import iti.jets.domain.entities.Project;
import iti.jets.persistence.DepartmentRepository;
import iti.jets.persistence.JpaManager;
import jakarta.persistence.EntityManager;

import java.util.Set;

public class DepartmentService {
    public void createDepartment(Department department) {
        DepartmentRepository departmentRepository = new DepartmentRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        entityManager.getTransaction().begin();
        departmentRepository.create(entityManager, department);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Department findDepartment(int departmentId) {
        DepartmentRepository departmentRepository = new DepartmentRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        Department department = departmentRepository.find(entityManager, departmentId);
        entityManager.close();
        return department;
    }

    public Department updateDepartment(Department department) {
        DepartmentRepository departmentRepository = new DepartmentRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        entityManager.getTransaction().begin();
        Department updatedDepartment = departmentRepository.update(entityManager, department);
        entityManager.getTransaction().commit();
        entityManager.close();
        return updatedDepartment;
    }

    public void deleteDepartment(int departmentId) {
        DepartmentRepository departmentRepository = new DepartmentRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        entityManager.getTransaction().begin();
        departmentRepository.delete(entityManager, departmentId);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Employee getDepartmentManager(int departmentId) {
        DepartmentRepository departmentRepository = new DepartmentRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        Department department = departmentRepository.find(entityManager, departmentId);
        Employee ret = null;
        if (department != null) {
            ret = department.getManager();
            ret.getEmail();
        }
        entityManager.close();
        return ret;
    }

    public Set<Project> getDepartmentProjects(int departmentId) {
        DepartmentRepository departmentRepository = new DepartmentRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        Department department = departmentRepository.find(entityManager, departmentId);
        Set<Project> ret = null;
        if (department != null) {
            ret = department.getProjects();
            ret.size();
        }
        entityManager.close();
        return ret;
    }

    public Set<Employee> getDepartmentEmployees(int departmentId) {
        DepartmentRepository departmentRepository = new DepartmentRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        Department department = departmentRepository.find(entityManager, departmentId);
        Set<Employee> ret = null;
        if (department != null) {
            ret = department.getEmployees();
            ret.size();
        }
        entityManager.close();
        return ret;
    }
}
