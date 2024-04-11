package iti.jets.domain.resources;


import iti.jets.domain.dtos.Department.DepartmentMapper;
import iti.jets.domain.dtos.Department.DepartmentRequest;
import iti.jets.domain.dtos.Department.DepartmentResponse;
import iti.jets.domain.dtos.Employee.EmployeeMapper;
import iti.jets.domain.dtos.Employee.EmployeeResponse;
import iti.jets.domain.dtos.Project.ProjectMapper;
import iti.jets.domain.dtos.Project.ProjectResponse;
import iti.jets.domain.entities.Department;
import iti.jets.domain.entities.Employee;
import iti.jets.domain.entities.Project;
import iti.jets.domain.services.DepartmentService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Set;
import java.util.stream.Collectors;

@Path("/department")
@Consumes({"application/json; qs=1", "application/xml; qs=0.75"})
@Produces({"application/json; qs=1", "application/xml; qs=0.75"})
public class DepartmentResource {

    @GET
    @Path("/{deptId}")
    public Response getDepartment(@PathParam("deptId") int deptId) {
        DepartmentService departmentService = new DepartmentService();
        Department department = departmentService.findDepartment(deptId);
        DepartmentResponse departmentResponse = DepartmentMapper.toDepartmentResponse(department);
        return Response.ok(departmentResponse).build();
    }

    @POST
    @Path("/add")
    public Response addDepartment(DepartmentRequest departmentRequest) {
        DepartmentService departmentService = new DepartmentService();
        Department department = DepartmentMapper.toDepartment(departmentRequest);
        departmentService.createDepartment(department);
        DepartmentResponse departmentResponse = DepartmentMapper.toDepartmentResponse(department);
        return Response.status(Response.Status.CREATED).entity(departmentResponse).build();
    }

    @PUT
    @Path("/update/{deptId}")
    public Response updateDepartment(@PathParam("deptId") int deptId, DepartmentRequest departmentRequest) {
        DepartmentService departmentService = new DepartmentService();
        Department department = DepartmentMapper.toDepartment(departmentRequest);
        department.setId(deptId);
        departmentService.updateDepartment(department);
        DepartmentResponse departmentResponse = DepartmentMapper.toDepartmentResponse(department);
        return Response.ok(departmentResponse).build();
    }

    @DELETE
    @Path("/delete/{deptId}")
    public Response deleteDepartment(@PathParam("deptId") int deptId) {
        DepartmentService departmentService = new DepartmentService();
        departmentService.deleteDepartment(deptId);
        return Response.ok().build();
    }

    @GET
    @Path("/{deptId}/manager")
    public Response getDepartmentManager(@PathParam("deptId") int deptId) {
        DepartmentService departmentService = new DepartmentService();
        Employee employee = departmentService.getDepartmentManager(deptId);
        EmployeeResponse employeeResponse = EmployeeMapper.toEmployeeResponse(employee);
        return Response.ok(employeeResponse).build();
    }

    @GET
    @Path("/{deptId}/employees")
    public Response getDepartmentEmployees(@PathParam("deptId") int deptId) {
        DepartmentService departmentService = new DepartmentService();
        Set<Employee> ret = departmentService.getDepartmentEmployees(deptId);
        Set<EmployeeResponse> employees = ret.stream()
                .map(EmployeeMapper::toEmployeeResponse)
                .collect(Collectors.toSet());
        return Response.ok(employees).build();
    }

    @GET
    @Path("/{deptId}/projects")
    public Response getDepartmentProjects(@PathParam("deptId") int deptId) {
        DepartmentService departmentService = new DepartmentService();
        Set<Project> ret = departmentService.getDepartmentProjects(deptId);
        Set<ProjectResponse> projects = ret.stream()
                .map(ProjectMapper::toProjectResponse)
                .collect(Collectors.toSet());
        return Response.ok(projects).build();
    }
}
