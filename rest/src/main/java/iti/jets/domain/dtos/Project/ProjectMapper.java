package iti.jets.domain.dtos.Project;

import iti.jets.domain.entities.Project;
import iti.jets.domain.services.DepartmentService;

public class ProjectMapper {
    public static ProjectResponse toProjectResponse(Project project) {
        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setProjectId(project.getId());
        projectResponse.setProjectName(project.getProjectName());
        projectResponse.setStartDate(project.getStartDate());
        projectResponse.setEndDate(project.getEndDate());
        projectResponse.setDepartmentId(project.getDepartment().getId());
        return projectResponse;
    }

    public static Project toProject(ProjectRequest projectRequest) {
        Project project = new Project();
        project.setProjectName(projectRequest.getProjectName());
        project.setStartDate(projectRequest.getStartDate());
        project.setEndDate(projectRequest.getEndDate());
        project.setDepartment(new DepartmentService().findDepartment(projectRequest.getDepartmentId()));
        return project;
    }
}
