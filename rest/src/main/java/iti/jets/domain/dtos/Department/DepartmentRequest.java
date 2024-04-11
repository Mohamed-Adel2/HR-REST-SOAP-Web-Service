package iti.jets.domain.dtos.Department;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartmentRequest {
    private String departmentName;
    private Integer managerId;
}
