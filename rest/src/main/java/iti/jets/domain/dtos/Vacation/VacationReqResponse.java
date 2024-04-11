package iti.jets.domain.dtos.Vacation;

import iti.jets.domain.dtos.util.CustomLink;
import iti.jets.domain.dtos.util.LocalDateAdapter;
import iti.jets.domain.resources.rest.EmployeeResource;
import iti.jets.domain.resources.rest.VacationResource;
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
public class VacationReqResponse {
    private int requestId;
    private int employeeId;
    private int vacationId;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
    private String status;
    private ArrayList<CustomLink> links = new ArrayList<>();

    public void addLinks(UriInfo uriInfo){
        String employeeLink = uriInfo.getBaseUriBuilder().path(EmployeeResource.class)
                .path(EmployeeResource.class, "getEmployee")
                .resolveTemplate("empId", employeeId)
                .build()
                .toString();
        CustomLink employee = new CustomLink();
        employee.setLink(employeeLink);
        employee.setRel("vacation's employee");
        links.add(employee);

        String vacationLink = uriInfo.getBaseUriBuilder().path(VacationResource.class)
                .path(VacationResource.class, "getVacationType")
                .resolveTemplate("vacationTypeId", vacationId)
                .build()
                .toString();
        CustomLink vacation = new CustomLink();
        vacation.setLink(vacationLink);
        vacation.setRel("vacation's type");
        links.add(vacation);

        String selfLink = uriInfo.getBaseUriBuilder().path(VacationResource.class)
                .path(VacationResource.class, "getVacation")
                .resolveTemplate("reqId", requestId)
                .build()
                .toString();
        CustomLink self = new CustomLink();
        self.setLink(selfLink);
        self.setRel("self");
        links.add(self);
    }
}
