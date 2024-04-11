package iti.jets.domain.dtos.Project;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ProjectResponse {
    private int projectId;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int departmentId;
}
