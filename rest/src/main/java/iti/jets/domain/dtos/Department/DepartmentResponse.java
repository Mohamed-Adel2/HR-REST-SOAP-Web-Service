package iti.jets.domain.dtos.Department;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartmentResponse {
    private String departmentName;
    private int departmentId;
    private int managerId;
}
