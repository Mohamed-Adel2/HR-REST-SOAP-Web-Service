package iti.jets.domain.services;

import iti.jets.domain.entities.Employee;
import iti.jets.domain.entities.Project;
import iti.jets.domain.exceptions.models.DataNotModifiedException;
import iti.jets.persistence.EmployeeRepository;
import iti.jets.persistence.JpaManager;
import iti.jets.persistence.ProjectRepository;
import jakarta.persistence.EntityManager;

import java.util.Set;

public class ProjectService {
    public void createProject(Project project) {
        ProjectRepository projectRepository = new ProjectRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        entityManager.getTransaction().begin();
        projectRepository.create(entityManager, project);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Project findProject(int projectId) {
        ProjectRepository projectRepository = new ProjectRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        Project project = projectRepository.find(entityManager, projectId);
        entityManager.close();
        return project;
    }

    public Project updateProject(Project project) {
        ProjectRepository projectRepository = new ProjectRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Project updatedProject = projectRepository.update(entityManager, project);
            entityManager.getTransaction().commit();
            entityManager.close();
            return updatedProject;
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
            entityManager.close();
            throw new DataNotModifiedException("Project with id " + project.getId() + " is not updated");
        }

    }

    public void deleteProject(int projectId) {
        ProjectRepository projectRepository = new ProjectRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        entityManager.getTransaction().begin();
        projectRepository.delete(entityManager, projectId);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Set<Employee> getProjectEmployees(int projectId) {
        ProjectRepository projectRepository = new ProjectRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        Project project = projectRepository.find(entityManager, projectId);
        Set<Employee> ret = null;
        if (project != null) {
            ret = project.getEmployees();
            ret.size();
        }
        entityManager.close();
        return ret;
    }

    public Set<Employee> addEmployeeToProject(int projectId, int employeeId) {
        ProjectRepository projectRepository = new ProjectRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        Employee employee = employeeRepository.find(entityManager, employeeId);
        if (employee != null) {
            entityManager.getTransaction().begin();
            Project project = projectRepository.addEmployee(entityManager, projectId, employee);
            Set<Employee> ret = project.getEmployees();
            ret.size();
            entityManager.getTransaction().commit();
            entityManager.close();
            return ret;
        }
        return null;
    }

    public Set<Employee> removeEmployeeFromProject(int projectId, int employeeId) {
        ProjectRepository projectRepository = new ProjectRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        EntityManager entityManager = JpaManager.createEntityManager();
        Employee employee = employeeRepository.find(entityManager, employeeId);
        if (employee != null) {
            entityManager.getTransaction().begin();
            Project project = projectRepository.removeEmployee(entityManager, projectId, employee);
            Set<Employee> ret = project.getEmployees();
            ret.size();
            entityManager.getTransaction().commit();
            entityManager.close();
            return ret;
        }
        return null;
    }
}
