package iti.jets.domain.resources;

import iti.jets.domain.dtos.Vacation.VacationMapper;
import iti.jets.domain.dtos.Vacation.VacationReqRequest;
import iti.jets.domain.dtos.Vacation.VacationReqResponse;
import iti.jets.domain.dtos.VacationType.VacationTypeMapper;
import iti.jets.domain.dtos.VacationType.VacationTypeRequest;
import iti.jets.domain.dtos.VacationType.VacationTypeResponse;
import iti.jets.domain.entities.VacationRequest;
import iti.jets.domain.entities.VacationType;
import iti.jets.domain.services.VacationRequestService;
import iti.jets.domain.services.VacationTypeService;
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

    @POST
    @Path("/types/add")
    public Response addVacationType(VacationTypeRequest vacationTypeReqRequest) {
        VacationTypeService vacationTypeService = new VacationTypeService();
        VacationType vacationType = VacationTypeMapper.toVacationType(vacationTypeReqRequest);
        vacationTypeService.createVacationType(vacationType);
        VacationTypeResponse vacationTypeResponse = VacationTypeMapper.toVacationTypeResponse(vacationType);
        return Response.status(Response.Status.CREATED).entity(vacationTypeResponse).build();
    }


    @GET
    @Path("/types/{vacationTypeId}")
    public Response getVacationType(@PathParam("vacationTypeId") int vacationTypeId) {
        VacationTypeService vacationTypeService = new VacationTypeService();
        VacationType vacationType = vacationTypeService.findVacationType(vacationTypeId);
        VacationTypeResponse vacationTypeResponse = VacationTypeMapper.toVacationTypeResponse(vacationType);
        return Response.ok(vacationTypeResponse).build();
    }

    @PUT
    @Path("/types/update/{vacationTypeId}")
    public Response updateVacationType(@PathParam("vacationTypeId") int vacationTypeId, VacationTypeRequest vacationTypeReqRequest) {
        VacationTypeService vacationTypeService = new VacationTypeService();
        VacationType vacationType = VacationTypeMapper.toVacationType(vacationTypeReqRequest);
        vacationType.setId(vacationTypeId);
        vacationTypeService.updateVacationType(vacationType);
        VacationTypeResponse vacationTypeResponse = VacationTypeMapper.toVacationTypeResponse(vacationType);
        return Response.ok(vacationTypeResponse).build();
    }
}
