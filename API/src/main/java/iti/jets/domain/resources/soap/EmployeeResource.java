package iti.jets.domain.resources.soap;

import iti.jets.domain.dtos.Department.DepartmentMapper;
import iti.jets.domain.dtos.Department.DepartmentResponse;
import iti.jets.domain.dtos.Employee.EmployeeMapper;
import iti.jets.domain.dtos.Employee.EmployeeRequest;
import iti.jets.domain.dtos.Employee.EmployeeResponse;
import iti.jets.domain.dtos.Project.ProjectMapper;
import iti.jets.domain.dtos.Project.ProjectResponse;
import iti.jets.domain.dtos.Vacation.VacationMapper;
import iti.jets.domain.dtos.Vacation.VacationReqResponse;
import iti.jets.domain.entities.Employee;
import iti.jets.domain.entities.Project;
import iti.jets.domain.entities.VacationRequest;
import iti.jets.domain.services.EmployeeService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.xml.ws.BindingType;

import java.util.Set;
import java.util.stream.Collectors;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
@BindingType(jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class EmployeeResource {

    @WebMethod
    public EmployeeResponse getEmployee(@WebParam(name = "empId") int empId) {
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.findEmployee(empId);
        return EmployeeMapper.toEmployeeResponse(employee);
    }

    @WebMethod
    public EmployeeResponse addEmployee(EmployeeRequest employee) {
        EmployeeService employeeService = new EmployeeService();
        Employee addedEmployee = EmployeeMapper.toEmployee(employee);
        employeeService.addEmployee(addedEmployee);
        return EmployeeMapper.toEmployeeResponse(addedEmployee);
    }

    @WebMethod
    public EmployeeResponse updateEmployee(@WebParam(name = "empId") int empId, EmployeeRequest employee) {
        EmployeeService employeeService = new EmployeeService();
        Employee updatedEmployee = EmployeeMapper.toEmployee(employee);
        updatedEmployee.setId(empId);
        updatedEmployee = employeeService.updateEmployee(updatedEmployee);
        return EmployeeMapper.toEmployeeResponse(updatedEmployee);
    }

    @WebMethod
    public Set<ProjectResponse> getEmployeeProjects(@WebParam(name = "empId") int empId) {
        EmployeeService employeeService = new EmployeeService();
        Set<Project> projects = employeeService.getEmployeeProjects(empId);
        return projects.stream().map(ProjectMapper::toProjectResponse).collect(Collectors.toSet());
    }

    @WebMethod
    public Set<VacationReqResponse> getEmployeeVacationRequests(@WebParam(name = "empId") int empId) {
        EmployeeService employeeService = new EmployeeService();
        Set<VacationRequest> vacationRequests = employeeService.getEmployeeVacationRequests(empId);
        return vacationRequests.stream().map(VacationMapper::toVacationReqResponse).collect(Collectors.toSet());
    }

    @WebMethod
    public DepartmentResponse getEmployeeDepartment(@WebParam(name = "empId") int empId) {
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.findEmployee(empId);
        return DepartmentMapper.toDepartmentResponse(employee.getDepartment());
    }

    @WebMethod
    public DepartmentResponse getEmployeeManagedDepartment(@WebParam(name = "empId") int empId) {
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.findEmployee(empId);
        return DepartmentMapper.toDepartmentResponse(employee.getManagedDepartment());
    }
}
