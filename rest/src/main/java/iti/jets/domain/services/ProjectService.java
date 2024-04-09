package iti.jets.domain.services;

import iti.jets.domain.entities.Employee;
import iti.jets.domain.entities.Project;
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
        entityManager.getTransaction().begin();
        Project updatedProject = projectRepository.update(entityManager, project);
        entityManager.getTransaction().commit();
        entityManager.close();
        return updatedProject;
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
        }
        entityManager.close();
        return ret;
    }
}
