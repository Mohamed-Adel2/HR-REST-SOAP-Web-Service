package iti.jets.persistence;

import iti.jets.domain.entities.Employee;
import iti.jets.domain.entities.Project;
import jakarta.persistence.EntityManager;

public class ProjectRepository extends AbstractRepository<Project> {
    public ProjectRepository() {
        super(Project.class);
    }

    public Project addEmployee(EntityManager em, int projectId, Employee employee) {
        Project project = em.find(Project.class, projectId);
        if (project != null) {
            project.addEmployee(employee);
        }
        return em.merge(project);
    }

    public Project removeEmployee(EntityManager em, int projectId, Employee employee) {
        Project project = em.find(Project.class, projectId);
        if (project != null) {
            project.removeEmployee(employee);
        }
        return em.merge(project);
    }
}
