package iti.jets.persistence;

import iti.jets.domain.entities.Project;

public class ProjectRepository extends AbstractRepository<Project>{
    public ProjectRepository() {
        super(Project.class);
    }
}
