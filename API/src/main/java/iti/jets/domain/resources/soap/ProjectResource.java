package iti.jets.domain.resources.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

import iti.jets.domain.dtos.Department.DepartmentMapper;
import iti.jets.domain.dtos.Department.DepartmentResponse;
import iti.jets.domain.dtos.Employee.EmployeeMapper;
import iti.jets.domain.dtos.Employee.EmployeeResponse;
import iti.jets.domain.dtos.Project.ProjectMapper;
import iti.jets.domain.dtos.Project.ProjectRequest;
import iti.jets.domain.dtos.Project.ProjectResponse;
import iti.jets.domain.entities.Project;
import iti.jets.domain.services.ProjectService;
import jakarta.xml.ws.BindingType;

import java.util.Set;
import java.util.stream.Collectors;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
@BindingType(jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class ProjectResource {

    @WebMethod
    public ProjectResponse getProject(@WebParam(name = "projectId") int projectId) {
        ProjectService projectService = new ProjectService();
        Project project = projectService.findProject(projectId);
        return ProjectMapper.toProjectResponse(project);
    }

    @WebMethod
    public ProjectResponse addProject(ProjectRequest projectRequest) {
        Project project = ProjectMapper.toProject(projectRequest);
        ProjectService projectService = new ProjectService();
        projectService.createProject(project);
        return ProjectMapper.toProjectResponse(project);
    }

    @WebMethod
    public ProjectResponse updateProject(@WebParam(name = "projectId") int projectId, ProjectRequest projectRequest) {
        Project project = ProjectMapper.toProject(projectRequest);
        project.setId(projectId);
        ProjectService projectService = new ProjectService();
        projectService.updateProject(project);
        return ProjectMapper.toProjectResponse(project);
    }

    @WebMethod
    public void deleteProject(@WebParam(name = "projectId") int projectId) {
        ProjectService projectService = new ProjectService();
        projectService.deleteProject(projectId);
    }

    @WebMethod
    public Set<EmployeeResponse> getProjectEmployees(@WebParam(name = "projectId") int projectId) {
        ProjectService projectService = new ProjectService();
        return projectService.getProjectEmployees(projectId).stream().map(EmployeeMapper::toEmployeeResponse).collect(Collectors.toSet());
    }

    @WebMethod
    public DepartmentResponse getProjectDepartment(@WebParam(name = "projectId") int projectId) {
        ProjectService projectService = new ProjectService();
        Project project = projectService.findProject(projectId);
        return DepartmentMapper.toDepartmentResponse(project.getDepartment());
    }

    @WebMethod
    public Set<EmployeeResponse> addEmployeeToProject(@WebParam(name = "projectId") int projectId, @WebParam(name = "empId") int empId) {
        ProjectService projectService = new ProjectService();
        return projectService.addEmployeeToProject(projectId, empId).stream().map(EmployeeMapper::toEmployeeResponse).collect(Collectors.toSet());
    }

    @WebMethod
    public Set<EmployeeResponse> removeEmployeeFromProject(@WebParam(name = "projectId") int projectId, @WebParam(name = "empId") int empId) {
        ProjectService projectService = new ProjectService();
        return projectService.removeEmployeeFromProject(projectId, empId).stream().map(EmployeeMapper::toEmployeeResponse).collect(Collectors.toSet());
    }
}