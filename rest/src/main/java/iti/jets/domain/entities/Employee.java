package iti.jets.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 255)
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Size(max = 20)
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Size(max = 100)
    @Column(name = "job_title", length = 100)
    private String jobTitle;

    @Column(name = "salary", precision = 10, scale = 2)
    private Double salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne(mappedBy = "manager")
    private Department departmentManaged;

    @OneToMany(mappedBy = "employee")
    private Set<VacationRequest> vacationRequests = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "employees")
    private Set<Project> projects = new LinkedHashSet<>();

}