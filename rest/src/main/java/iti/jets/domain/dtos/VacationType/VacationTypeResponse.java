package iti.jets.domain.dtos.VacationType;

import iti.jets.domain.dtos.util.CustomLink;
import iti.jets.domain.resources.VacationResource;
import jakarta.ws.rs.core.UriInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class VacationTypeResponse {
    private int vacationTypeId;
    private String vacationName;
    private ArrayList<CustomLink> links = new ArrayList<>();

    public void addLinks(UriInfo uriInfo){
        String selfLink = uriInfo.getBaseUriBuilder().path(VacationResource.class)
                .path(VacationResource.class, "getVacationType")
                .resolveTemplate("vacationTypeId", vacationTypeId)
                .build()
                .toString();
        CustomLink self = new CustomLink();
        self.setLink(selfLink);
        self.setRel("self");
        links.add(self);
    }
}
