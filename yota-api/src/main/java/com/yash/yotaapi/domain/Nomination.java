package com.yash.yotaapi.domain;
 
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;
 
/**
* Represents a Nomination entity.
* This class defines the structure and attributes of a Nomination.
* @author raghav.muchhal
*/
@Entity
@Table(name = "nomination")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nomination {
 
    /** Unique identifier for the nomination. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nominationId;
 
    /** Employee ID associated with the nomination. */
    @NotNull
    private Long employeeId;
 
    /** Name of the employee nominated. */
    @NotNull
    private String employeeName;
 
    /** Email of the nominated employee. */
    @NotNull
    private String employeeEmail;
 
    /** Skills set updated during the nomination. */
    private String updateSkillsSet;
 
    /** Current skills set of the nominated employee. */
    private String currentSkillsSet;
 
    /** Current status of the nomination. */
    private String currentStatus;
 
    /** Set of trainings associated with the nomination. */
    @ManyToMany
    @JoinTable(
        name = "nomination_training",
        joinColumns = @JoinColumn(name = "nomination_id"),
        inverseJoinColumns = @JoinColumn(name = "training_id")
    )
    private Set<Training> trainings = new HashSet<>();
}