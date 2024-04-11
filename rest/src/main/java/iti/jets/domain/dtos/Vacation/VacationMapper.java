package iti.jets.domain.dtos.Vacation;

import iti.jets.domain.entities.Employee;
import iti.jets.domain.entities.VacationRequest;
import iti.jets.domain.services.EmployeeService;
import iti.jets.domain.services.VacationTypeService;

public class VacationMapper {
    public static VacationReqResponse toVacationReqResponse(VacationRequest vacationRequest) {
        VacationReqResponse vacationReqResponse = new VacationReqResponse();
        vacationReqResponse.setRequestId(vacationRequest.getId());
        vacationReqResponse.setEmployeeId(vacationRequest.getEmployee().getId());
        vacationReqResponse.setVacationId(vacationRequest.getVacation().getId());
        vacationReqResponse.setStartDate(vacationRequest.getStartDate());
        vacationReqResponse.setEndDate(vacationRequest.getEndDate());
        vacationReqResponse.setStatus(vacationRequest.getStatus());
        return vacationReqResponse;
    }

    public static VacationRequest toVacationRequest(VacationReqRequest vacationReqRequest) {
        VacationRequest vacationRequest = new VacationRequest();
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.findEmployee(vacationReqRequest.getEmployeeId());
        vacationRequest.setEmployee(employee);
        VacationTypeService vacationTypeService = new VacationTypeService();
        vacationRequest.setVacation(vacationTypeService.findVacationType(vacationReqRequest.getVacationId()));
        vacationRequest.setStartDate(vacationReqRequest.getStartDate());
        vacationRequest.setEndDate(vacationReqRequest.getEndDate());
        vacationRequest.setStatus(vacationReqRequest.getStatus());
        return vacationRequest;
    }
}
