package iti.jets.domain.dtos.Employee;

import iti.jets.domain.dtos.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate hireDate;
    private String jobTitle;
    private Double salary;
    private int departmentId;
}