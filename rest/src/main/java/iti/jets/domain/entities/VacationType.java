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
@Table(name = "vacationtypes")
public class VacationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "vacation_name", length = 100)
    private String vacationName;

    @OneToMany(mappedBy = "vacation")
    private Set<VacationRequest> vacationRequests = new LinkedHashSet<>();

}