package iti.jets.domain.dtos.Project;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ProjectRequest {
    private String projectName;
    private int departmentId;
    private LocalDate startDate;
    private LocalDate endDate;
}
