package iti.jets.domain.dtos.VacationType;

import iti.jets.domain.entities.VacationType;

public class VacationTypeMapper {
    public static VacationTypeResponse toVacationTypeResponse(VacationType vacationType) {
        VacationTypeResponse vacationTypeResponse = new VacationTypeResponse();
        vacationTypeResponse.setVacationTypeId(vacationType.getId());
        vacationTypeResponse.setVacationName(vacationType.getVacationName());
        return vacationTypeResponse;
    }

    public static VacationType toVacationType(VacationTypeRequest vacationTypeRequest) {
        VacationType vacationType = new VacationType();
        vacationType.setVacationName(vacationTypeRequest.getVacationName());
        return vacationType;
    }
}
