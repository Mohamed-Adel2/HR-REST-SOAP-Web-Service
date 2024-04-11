package iti.jets.domain.resources;

import iti.jets.domain.dtos.Department.DepartmentMapper;
import iti.jets.domain.dtos.Department.DepartmentResponse;
import iti.jets.domain.dtos.Employee.EmployeeResponse;
import iti.jets.domain.dtos.Employee.EmployeeMapper;
import iti.jets.domain.dtos.Employee.EmployeeRequest;
import iti.jets.domain.dtos.Project.ProjectMapper;
import iti.jets.domain.dtos.Project.ProjectResponse;
import iti.jets.domain.dtos.Vacation.VacationMapper;
import iti.jets.domain.dtos.Vacation.VacationReqResponse;
import iti.jets.domain.entities.Employee;
import iti.jets.domain.entities.Project;
import iti.jets.domain.entities.VacationRequest;
import iti.jets.domain.services.EmployeeService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Set;
import java.util.stream.Collectors;

@Path("/employee")
@Consumes({"application/json; qs=1", "application/xml; qs=0.75"})
@Produces({"application/json; qs=1", "application/xml; qs=0.75"})
public class EmployeeResource {
    @GET
    @Path("/{empId}")
    public Response getEmployee(@PathParam("empId") int empId) {
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.findEmployee(empId);
        EmployeeResponse employeeResponse = EmployeeMapper.toEmployeeResponse(employee);
        return Response.ok(employeeResponse).build();
    }

    @POST
    @Path("/add")
    public Response addEmployee(EmployeeRequest employee) {
        EmployeeService employeeService = new EmployeeService();
        Employee addedEmployee = EmployeeMapper.toEmployee(employee);
        employeeService.addEmployee(addedEmployee);
        EmployeeResponse employeeResponse = EmployeeMapper.toEmployeeResponse(addedEmployee);
        return Response.status(Response.Status.CREATED).entity(employeeResponse).build();
    }

    @PUT
    @Path("/update/{empId}")
    public Response updateEmployee(@PathParam("empId") int empId, EmployeeRequest employee) {
        EmployeeService employeeService = new EmployeeService();
        Employee updatedEmployee = EmployeeMapper.toEmployee(employee);
        updatedEmployee.setId(empId);
        updatedEmployee = employeeService.updateEmployee(updatedEmployee);
        EmployeeResponse employeeResponse = EmployeeMapper.toEmployeeResponse(updatedEmployee);
        return Response.ok(employeeResponse).build();
    }

    @GET
    @Path("/{empId}/projects")
    public Response getEmployeeProjects(@PathParam("empId") int empId) {
        EmployeeService employeeService = new EmployeeService();
        Set<Project> projects = employeeService.getEmployeeProjects(empId);
        Set<ProjectResponse> projectResponses = projects.stream().map(ProjectMapper::toProjectResponse).collect(Collectors.toSet());
        return Response.ok(projectResponses).build();
    }

    @GET
    @Path("/{empId}/vacations")
    public Response getEmployeeVacationRequests(@PathParam("empId") int empId) {
        EmployeeService employeeService = new EmployeeService();
        Set<VacationRequest> vacationRequests = employeeService.getEmployeeVacationRequests(empId);
        Set<VacationReqResponse> vacationRequestResponses = vacationRequests.stream().map(VacationMapper::toVacationReqResponse).collect(Collectors.toSet());
        return Response.ok(vacationRequestResponses).build();
    }

    @GET
    @Path("/{empId}/department")
    public Response getEmployeeDepartment(@PathParam("empId") int empId) {
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.findEmployee(empId);
        DepartmentResponse departmentResponse = DepartmentMapper.toDepartmentResponse(employee.getDepartment());
        return Response.ok(departmentResponse).build();
    }

    @GET
    @Path("/{empId}/managedDepartment")
    public Response getEmployeeManagedDepartment(@PathParam("empId") int empId) {
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.findEmployee(empId);
        DepartmentResponse departmentResponse = DepartmentMapper.toDepartmentResponse(employee.getManagedDepartment());
        return Response.ok(departmentResponse).build();
    }
}
