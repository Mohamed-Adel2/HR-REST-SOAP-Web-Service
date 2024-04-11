package iti.jets.domain.dtos.Vacation;

import iti.jets.domain.dtos.util.CustomLink;
import iti.jets.domain.resources.EmployeeResource;
import iti.jets.domain.resources.VacationResource;
import jakarta.ws.rs.core.UriInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

@Setter
@Getter
public class VacationReqResponse {
    private int RequestId;
    private int EmployeeId;
    private int VacationId;
    private LocalDate StartDate;
    private LocalDate EndDate;
    private String Status;
    private ArrayList<CustomLink> links = new ArrayList<>();

    public void addLinks(UriInfo uriInfo){
        String employeeLink = uriInfo.getBaseUriBuilder().path(EmployeeResource.class)
                .path(EmployeeResource.class, "getEmployee")
                .resolveTemplate("empId", EmployeeId)
                .build()
                .toString();
        CustomLink employee = new CustomLink();
        employee.setLink(employeeLink);
        employee.setRel("employee");
        links.add(employee);

        String vacationLink = uriInfo.getBaseUriBuilder().path(VacationResource.class)
                .path(VacationResource.class, "getVacationType")
                .resolveTemplate("vacationTypeId", VacationId)
                .build()
                .toString();
        CustomLink vacation = new CustomLink();
        vacation.setLink(vacationLink);
        vacation.setRel("vacation");
        links.add(vacation);

        String selfLink = uriInfo.getBaseUriBuilder().path(VacationResource.class)
                .path(VacationResource.class, "getVacation")
                .resolveTemplate("reqId", RequestId)
                .build()
                .toString();
        CustomLink self = new CustomLink();
        self.setLink(selfLink);
        self.setRel("self");
        links.add(self);
    }
}
