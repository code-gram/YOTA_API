package com.yash.yotaapi.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainings")

public class Trainings {

	@Id
    @Column(name = "id")
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	@SequenceGenerator(initialValue = 1, name = "id")
	private Long id;

	@NotNull(message = "Training Name should not be empty")
	private String trainingName;

	@NotNull
	@Size(min = 1,message = "Assign To should not be empty")
	private String assignTo;


	@NotNull(message = "Start Date should be empty")
	private Date startDate;


	@NotNull(message = "End Date should be empty")
	private Date endDate;

	private String status;

	@PositiveOrZero(message = "Total Nominations cannot be Negative")
	private int totalNominations;
	
	private int registeredInTraining;

	@ManyToMany
	private List<YotaUser> assign;

	@ManyToMany
	@JoinTable(name = "training_test_assign",
			joinColumns = @JoinColumn(name = "training_id"),
			inverseJoinColumns = @JoinColumn(name = "test_id"))
	private List<Tests> tests;
}
