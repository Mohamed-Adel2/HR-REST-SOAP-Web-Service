package iti.jets.domain.dtos.Employee;

import iti.jets.domain.dtos.util.CustomLink;
import iti.jets.domain.resources.EmployeeResource;
import jakarta.ws.rs.core.UriInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

@Setter
@Getter
public class EmployeeResponse {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private String jobTitle;
    private String salary;
    private int departmentId;
    private ArrayList<CustomLink> links = new ArrayList<>();

    public void addLinks(UriInfo uriInfo) {
        String projectsLink = uriInfo.getBaseUriBuilder().path(EmployeeResource.class)
                .path(EmployeeResource.class, "getEmployeeProjects")
                .resolveTemplate("empId", employeeId)
                .build()
                .toString();
        CustomLink projects = new CustomLink();
        projects.setLink(projectsLink);
        projects.setRel("projects");
        links.add(projects);

        String vacationRequestsLink = uriInfo.getBaseUriBuilder().path(EmployeeResource.class)
                .path(EmployeeResource.class, "getEmployeeVacationRequests")
                .resolveTemplate("empId", employeeId)
                .build()
                .toString();
        CustomLink vacationRequests = new CustomLink();
        vacationRequests.setLink(vacationRequestsLink);
        vacationRequests.setRel("vacationRequests");
        links.add(vacationRequests);

        String departmentLink = uriInfo.getBaseUriBuilder().path(EmployeeResource.class)
                .path(EmployeeResource.class, "getEmployeeDepartment")
                .resolveTemplate("empId", employeeId)
                .build()
                .toString();
        CustomLink department = new CustomLink();
        department.setLink(departmentLink);
        department.setRel("department");
        links.add(department);

        String selfLink = uriInfo.getBaseUriBuilder().path(EmployeeResource.class)
                .resolveTemplate("empId", employeeId)
                .build()
                .toString();
        CustomLink self = new CustomLink();
        self.setLink(selfLink);
        self.setRel("self");
        links.add(self);
    }

}
