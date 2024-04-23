package iti.jets.domain.dtos.Department;

import iti.jets.domain.entities.Department;
import iti.jets.domain.services.EmployeeService;

public class DepartmentMapper {
    public static DepartmentResponse toDepartmentResponse(Department department) {
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setDepartmentName(department.getDepartmentName());
        departmentResponse.setDepartmentId(department.getId());
        if (department.getManager() != null)
            departmentResponse.setManagerId(department.getManager().getId());
        return departmentResponse;
    }

    public static Department toDepartment(DepartmentRequest departmentRequest) {
        Department department = new Department();
        if (departmentRequest.getManagerId() != null)
            department.setManager(new EmployeeService().findEmployee(departmentRequest.getManagerId()));
        department.setDepartmentName(departmentRequest.getDepartmentName());
        return department;
    }
}
