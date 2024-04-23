package iti.jets.domain.resources.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

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
import jakarta.xml.ws.BindingType;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
@BindingType(jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class VacationResource {

    @WebMethod
    public VacationReqResponse getVacation(@WebParam(name = "reqId") int reqId) {
        VacationRequestService vacationRequestService = new VacationRequestService();
        VacationRequest vacationRequest = vacationRequestService.findVacationRequest(reqId);
        return VacationMapper.toVacationReqResponse(vacationRequest);
    }

    @WebMethod
    public VacationReqResponse addVacation(VacationReqRequest vacationReqRequest) {
        VacationRequestService vacationRequestService = new VacationRequestService();
        VacationRequest vacationRequest = VacationMapper.toVacationRequest(vacationReqRequest);
        vacationRequestService.createVacationRequest(vacationRequest);
        return VacationMapper.toVacationReqResponse(vacationRequest);
    }

    @WebMethod
    public VacationReqResponse updateVacation(@WebParam(name = "vacationId") int vacationId, VacationReqRequest vacationReqRequest) {
        VacationRequestService vacationRequestService = new VacationRequestService();
        VacationRequest vacationRequest = VacationMapper.toVacationRequest(vacationReqRequest);
        vacationRequest.setId(vacationId);
        vacationRequestService.updateVacationRequest(vacationRequest);
        return VacationMapper.toVacationReqResponse(vacationRequest);
    }

    @WebMethod
    public void deleteVacation(@WebParam(name = "vacationId") int vacationId) {
        VacationRequestService vacationRequestService = new VacationRequestService();
        vacationRequestService.deleteVacationRequest(vacationId);
    }

    @WebMethod
    public VacationTypeResponse addVacationType(VacationTypeRequest vacationTypeReqRequest) {
        VacationTypeService vacationTypeService = new VacationTypeService();
        VacationType vacationType = VacationTypeMapper.toVacationType(vacationTypeReqRequest);
        vacationTypeService.createVacationType(vacationType);
        return VacationTypeMapper.toVacationTypeResponse(vacationType);
    }

    @WebMethod
    public VacationTypeResponse getVacationType(@WebParam(name = "vacationTypeId") int vacationTypeId) {
        VacationTypeService vacationTypeService = new VacationTypeService();
        VacationType vacationType = vacationTypeService.findVacationType(vacationTypeId);
        return VacationTypeMapper.toVacationTypeResponse(vacationType);
    }

    @WebMethod
    public VacationTypeResponse updateVacationType(@WebParam(name = "vacationTypeId") int vacationTypeId, VacationTypeRequest vacationTypeReqRequest) {
        VacationTypeService vacationTypeService = new VacationTypeService();
        VacationType vacationType = VacationTypeMapper.toVacationType(vacationTypeReqRequest);
        vacationType.setId(vacationTypeId);
        vacationTypeService.updateVacationType(vacationType);
        return VacationTypeMapper.toVacationTypeResponse(vacationType);
    }
}