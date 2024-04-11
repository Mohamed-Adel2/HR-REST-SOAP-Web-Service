package iti.jets.domain.resources.soap;

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
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.BindingType;

import java.util.Set;
import java.util.stream.Collectors;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
@BindingType(jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class DepartmentResource {

    @WebMethod
    public DepartmentResponse getDepartment(@WebParam(name = "deptId") int deptId) {
        DepartmentService departmentService = new DepartmentService();
        Department department = departmentService.findDepartment(deptId);
        return DepartmentMapper.toDepartmentResponse(department);
    }

    @WebMethod
    public DepartmentResponse addDepartment(DepartmentRequest departmentRequest) {
        DepartmentService departmentService = new DepartmentService();
        Department department = DepartmentMapper.toDepartment(departmentRequest);
        departmentService.createDepartment(department);
        return DepartmentMapper.toDepartmentResponse(department);
    }

    @WebMethod
    public DepartmentResponse updateDepartment(@WebParam(name = "deptId") int deptId, DepartmentRequest departmentRequest) {
        DepartmentService departmentService = new DepartmentService();
        Department department = DepartmentMapper.toDepartment(departmentRequest);
        department.setId(deptId);
        department = departmentService.updateDepartment(department);
        return DepartmentMapper.toDepartmentResponse(department);
    }

    @WebMethod
    public void deleteDepartment(@WebParam(name = "deptId") int deptId) {
        DepartmentService departmentService = new DepartmentService();
        departmentService.deleteDepartment(deptId);
    }

    @WebMethod
    public EmployeeResponse getDepartmentManager(@WebParam(name = "deptId") int deptId) {
        DepartmentService departmentService = new DepartmentService();
        Employee employee = departmentService.getDepartmentManager(deptId);
        return EmployeeMapper.toEmployeeResponse(employee);
    }

    @WebMethod
    public Set<EmployeeResponse> getDepartmentEmployees(@WebParam(name = "deptId") int deptId) {
        DepartmentService departmentService = new DepartmentService();
        Set<Employee> ret = departmentService.getDepartmentEmployees(deptId);
        return ret.stream().map(EmployeeMapper::toEmployeeResponse).collect(Collectors.toSet());
    }

    @WebMethod
    public Set<ProjectResponse> getDepartmentProjects(@WebParam(name = "deptId") int deptId) {
        DepartmentService departmentService = new DepartmentService();
        Set<Project> ret = departmentService.getDepartmentProjects(deptId);
        return ret.stream().map(ProjectMapper::toProjectResponse).collect(Collectors.toSet());
    }
}