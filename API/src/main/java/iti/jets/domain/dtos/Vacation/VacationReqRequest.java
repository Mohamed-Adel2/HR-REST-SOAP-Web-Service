package iti.jets.domain.dtos.Vacation;

import iti.jets.domain.dtos.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class VacationReqRequest {
    private int employeeId;
    private int vacationId;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
    private String status;
}
