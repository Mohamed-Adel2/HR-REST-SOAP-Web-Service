package iti.jets.domain.dtos.Employee;

import iti.jets.domain.entities.Employee;
import iti.jets.domain.services.DepartmentService;

public class EmployeeMapper {
    public static Employee toEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        employee.setHireDate(employeeRequest.getHireDate());
        employee.setJobTitle(employeeRequest.getJobTitle());
        employee.setSalary(employeeRequest.getSalary());
        employee.setDepartment(new DepartmentService().findDepartment(employeeRequest.getDepartmentId()));
        return employee;
    }

    public static EmployeeResponse toEmployeeResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeId(employee.getId());
        employeeResponse.setFirstName(employee.getFirstName());
        employeeResponse.setLastName(employee.getLastName());
        employeeResponse.setEmail(employee.getEmail());
        employeeResponse.setPhoneNumber(employee.getPhoneNumber());
        employeeResponse.setHireDate(employee.getHireDate());
        employeeResponse.setJobTitle(employee.getJobTitle());
        employeeResponse.setSalary(String.valueOf(employee.getSalary()));
        employeeResponse.setDepartmentId(employee.getDepartment().getId());
        return employeeResponse;
    }
}
