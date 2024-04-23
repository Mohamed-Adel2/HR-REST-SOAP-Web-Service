package iti.jets.domain.dtos.Department;

import iti.jets.domain.dtos.util.CustomLink;
import iti.jets.domain.resources.rest.DepartmentResource;
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

    public void addLinks(UriInfo uriInfo) {
        String employeesLink = uriInfo.getBaseUriBuilder().path(DepartmentResource.class)
                .path(DepartmentResource.class, "getDepartmentEmployees")
                .resolveTemplate("deptId", departmentId)
                .build()
                .toString();
        CustomLink employees = new CustomLink();
        employees.setLink(employeesLink);
        employees.setRel("department's employees");
        links.add(employees);

        String projectsLink = uriInfo.getBaseUriBuilder().path(DepartmentResource.class)
                .path(DepartmentResource.class, "getDepartmentProjects")
                .resolveTemplate("deptId", departmentId)
                .build()
                .toString();
        CustomLink projects = new CustomLink();
        projects.setLink(projectsLink);
        projects.setRel("department's projects");
        links.add(projects);

        String managerLink = uriInfo.getBaseUriBuilder().path(DepartmentResource.class)
                .path(DepartmentResource.class, "getDepartmentManager")
                .resolveTemplate("deptId", departmentId)
                .build()
                .toString();
        CustomLink manager = new CustomLink();
        manager.setLink(managerLink);
        manager.setRel("department's manager");
        links.add(manager);

        String selfLink = uriInfo.getBaseUriBuilder().path(DepartmentResource.class)
                .path(DepartmentResource.class, "getDepartment")
                .resolveTemplate("deptId", departmentId)
                .build()
                .toString();
        CustomLink self = new CustomLink();
        self.setLink(selfLink);
        self.setRel("self");
        links.add(self);
    }
}
