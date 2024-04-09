package iti.jets.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "department_name", length = 100)
    private String departmentName;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees = new LinkedHashSet<>();

    @OneToMany(mappedBy = "department")
    private Set<Project> projects = new LinkedHashSet<>();

}