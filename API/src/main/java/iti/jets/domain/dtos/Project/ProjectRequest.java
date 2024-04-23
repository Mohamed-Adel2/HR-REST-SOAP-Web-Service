package iti.jets.domain.dtos.Project;

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
public class ProjectRequest {
    private String projectName;
    private int departmentId;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
}
