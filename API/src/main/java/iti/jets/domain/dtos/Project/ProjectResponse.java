package iti.jets.domain.dtos.Project;

import iti.jets.domain.dtos.util.CustomLink;
import iti.jets.domain.dtos.util.LocalDateAdapter;
import iti.jets.domain.resources.rest.ProjectResource;
import jakarta.ws.rs.core.UriInfo;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class ProjectResponse {
    private int projectId;
    private String projectName;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
    private int departmentId;
    private ArrayList<CustomLink> links = new ArrayList<>();

    public void addLinks(UriInfo uriInfo) {
        String employeesLink = uriInfo.getBaseUriBuilder().path(ProjectResource.class)
                .path(ProjectResource.class, "getProjectEmployees")
                .resolveTemplate("projectId", projectId)
                .build()
                .toString();
        CustomLink employees = new CustomLink();
        employees.setLink(employeesLink);
        employees.setRel("project's employees");
        links.add(employees);

        String departmentLink = uriInfo.getBaseUriBuilder().path(ProjectResource.class)
                .path(ProjectResource.class, "getProjectDepartment")
                .resolveTemplate("projectId", projectId)
                .build()
                .toString();
        CustomLink department = new CustomLink();
        department.setLink(departmentLink);
        department.setRel("project's department");
        links.add(department);

        String selfLink = uriInfo.getBaseUriBuilder().path(ProjectResource.class)
                .resolveTemplate("projectId", projectId)
                .build()
                .toString();
        CustomLink self = new CustomLink();
        self.setLink(selfLink);
        self.setRel("self");
        links.add(self);
    }
}
