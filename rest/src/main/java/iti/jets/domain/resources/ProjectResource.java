package iti.jets.domain.resources;


import iti.jets.domain.dtos.Department.DepartmentMapper;
import iti.jets.domain.dtos.Department.DepartmentResponse;
import iti.jets.domain.dtos.Employee.EmployeeMapper;
import iti.jets.domain.dtos.Employee.EmployeeResponse;
import iti.jets.domain.dtos.Project.ProjectMapper;
import iti.jets.domain.dtos.Project.ProjectRequest;
import iti.jets.domain.dtos.Project.ProjectResponse;
import iti.jets.domain.entities.Employee;
import iti.jets.domain.entities.Project;
import iti.jets.domain.services.EmployeeService;
import iti.jets.domain.services.ProjectService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.Set;
import java.util.stream.Collectors;

@Path("/project")
@Produces({"application/json; qs=1", "application/xml; qs=0.75"})
@Consumes({"application/json; qs=1", "application/xml; qs=0.75"})
public class ProjectResource {

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("/{projectId}")
    public Response getProject(@PathParam("projectId") int projectId) {
        ProjectService projectService = new ProjectService();
        Project project = projectService.findProject(projectId);
        ProjectResponse projectResponse = ProjectMapper.toProjectResponse(project);
        projectResponse.addLinks(uriInfo);
        return Response.ok(projectResponse).build();
    }

    @POST
    @Path("/add")
    public Response addProject(ProjectRequest projectRequest) {
        Project project = ProjectMapper.toProject(projectRequest);
        ProjectService projectService = new ProjectService();
        projectService.createProject(project);
        ProjectResponse projectResponse = ProjectMapper.toProjectResponse(project);
        projectResponse.addLinks(uriInfo);
        return Response.status(Response.Status.CREATED).entity(projectResponse).build();
    }

    @PUT
    @Path("/update/{projectId}")
    public Response updateProject(@PathParam("projectId") int projectId, ProjectRequest projectRequest) {
        Project project = ProjectMapper.toProject(projectRequest);
        project.setId(projectId);
        ProjectService projectService = new ProjectService();
        projectService.updateProject(project);
        ProjectResponse projectResponse = ProjectMapper.toProjectResponse(project);
        projectResponse.addLinks(uriInfo);
        return Response.ok(projectResponse).build();
    }

    @DELETE
    @Path("/delete/{projectId}")
    public Response deleteProject(@PathParam("projectId") int projectId) {
        ProjectService projectService = new ProjectService();
        projectService.deleteProject(projectId);
        return Response.ok().build();
    }

    @GET
    @Path("/{projectId}/employees")
    public Response getProjectEmployees(@PathParam("projectId") int projectId) {
        ProjectService projectService = new ProjectService();
        Set<EmployeeResponse> employees = projectService.getProjectEmployees(projectId).stream().map(EmployeeMapper::toEmployeeResponse).collect(Collectors.toSet());
        for (EmployeeResponse employeeResponse : employees) {
            employeeResponse.addLinks(uriInfo);
        }
        return Response.ok(employees).build();
    }

    @GET
    @Path("/{projectId}/department")
    public Response getProjectDepartment(@PathParam("projectId") int projectId) {
        ProjectService projectService = new ProjectService();
        Project project = projectService.findProject(projectId);
        DepartmentResponse departmentResponse = DepartmentMapper.toDepartmentResponse(project.getDepartment());
        departmentResponse.addLinks(uriInfo);
        return Response.ok(departmentResponse).build();
    }

    @PUT
    @Path("/{projectId}/addEmployee/{empId}")
    public Response addEmployeeToProject(@PathParam("projectId") int projectId, @PathParam("empId") int empId) {
        ProjectService projectService = new ProjectService();
        Set<Employee> ret = projectService.addEmployeeToProject(projectId, empId);
        Set<EmployeeResponse> employees = ret.stream().map(EmployeeMapper::toEmployeeResponse).collect(Collectors.toSet());
        for (EmployeeResponse employee : employees) {
            employee.addLinks(uriInfo);
        }
        return Response.ok().entity(employees).build();
    }

    @PUT
    @Path("/{projectId}/removeEmployee/{empId}")
    public Response removeEmployeeFromProject(@PathParam("projectId") int projectId, @PathParam("empId") int empId) {
        ProjectService projectService = new ProjectService();
        Set<Employee> ret = projectService.removeEmployeeFromProject(projectId, empId);
        Set<EmployeeResponse> employees = ret.stream().map(EmployeeMapper::toEmployeeResponse).collect(Collectors.toSet());
        for (EmployeeResponse employee : employees) {
            employee.addLinks(uriInfo);
        }
        return Response.ok().entity(employees).build();
    }
}
