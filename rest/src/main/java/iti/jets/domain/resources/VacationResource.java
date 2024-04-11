package iti.jets.domain.resources;

import iti.jets.domain.dtos.Vacation.VacationMapper;
import iti.jets.domain.dtos.Vacation.VacationReqRequest;
import iti.jets.domain.dtos.Vacation.VacationReqResponse;
import iti.jets.domain.entities.VacationRequest;
import iti.jets.domain.services.VacationRequestService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/vacation")
@Produces({"application/json", "application/xml"})
@Consumes({"application/json", "application/xml"})
public class VacationResource {
    @GET
    @Path("/{vacationId}")
    public Response getVacation(@PathParam("vacationId") int vacationId) {
        VacationRequestService vacationRequestService = new VacationRequestService();
        VacationRequest vacationRequest = vacationRequestService.findVacationRequest(vacationId);
        VacationReqResponse vacationReqResponse = VacationMapper.toVacationReqResponse(vacationRequest);
        return Response.ok(vacationReqResponse).build();
    }

    @POST
    @Path("/add")
    public Response addVacation(VacationReqRequest vacationReqRequest) {
        VacationRequestService vacationRequestService = new VacationRequestService();
        VacationRequest vacationRequest = VacationMapper.toVacationRequest(vacationReqRequest);
        vacationRequestService.createVacationRequest(vacationRequest);
        VacationReqResponse vacationReqResponse = VacationMapper.toVacationReqResponse(vacationRequest);
        return Response.status(Response.Status.CREATED).entity(vacationReqResponse).build();
    }

    @PUT
    @Path("/update/{vacationId}")
    public Response updateVacation(@PathParam("vacationId") int vacationId, VacationReqRequest vacationReqRequest) {
        VacationRequestService vacationRequestService = new VacationRequestService();
        VacationRequest vacationRequest = VacationMapper.toVacationRequest(vacationReqRequest);
        vacationRequest.setId(vacationId);
        vacationRequestService.updateVacationRequest(vacationRequest);
        VacationReqResponse vacationReqResponse = VacationMapper.toVacationReqResponse(vacationRequest);
        return Response.ok(vacationReqResponse).build();
    }

    @DELETE
    @Path("/delete/{vacationId}")
    public Response deleteVacation(@PathParam("vacationId") int vacationId) {
        VacationRequestService vacationRequestService = new VacationRequestService();
        vacationRequestService.deleteVacationRequest(vacationId);
        return Response.noContent().build();
    }
}
