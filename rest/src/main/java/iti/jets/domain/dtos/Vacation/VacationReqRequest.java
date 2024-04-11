package iti.jets.domain.dtos.Vacation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class VacationReqRequest {
    private int EmployeeId;
    private int VacationId;
    private LocalDate StartDate;
    private LocalDate EndDate;
    private String Status;
}
