package iti.jets.domain.dtos.Department;

import iti.jets.domain.dtos.util.CustomLink;
import jakarta.ws.rs.core.UriInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class DepartmentResponse {
    private String departmentName;
    private int departmentId;
    private int managerId;
    ArrayList<CustomLink> links = new ArrayList<>();

    public void addLinks(UriInfo uriInfo){
        String employeesLink = uriInfo.getBaseUriBuilder().path(DepartmentResponse.class)
                .path(DepartmentResponse.class, "getDepartmentEmployees")
                .queryParam("deptId", departmentId)
                .build()
                .toString();
        CustomLink employees = new CustomLink();
        employees.setLink(employeesLink);
        employees.setRel("employees");
        links.add(employees);

        String projectsLink = uriInfo.getBaseUriBuilder().path(DepartmentResponse.class)
                .path(DepartmentResponse.class, "getDepartmentProjects")
                .queryParam("deptId", departmentId)
                .build()
                .toString();
        CustomLink projects = new CustomLink();
        projects.setLink(projectsLink);
        projects.setRel("projects");
        links.add(projects);

        String managerLink = uriInfo.getBaseUriBuilder().path(DepartmentResponse.class)
                .path(DepartmentResponse.class, "getDepartmentManager")
                .queryParam("empId", managerId)
                .build()
                .toString();
        CustomLink manager = new CustomLink();
        manager.setLink(managerLink);
        manager.setRel("manager");
        links.add(manager);

        String selfLink = uriInfo.getBaseUriBuilder().path(DepartmentResponse.class)
                .resolveTemplate("deptId", departmentId)
                .build()
                .toString();
        CustomLink self = new CustomLink();
        self.setLink(selfLink);
        self.setRel("self");
        links.add(self);
    }
}
