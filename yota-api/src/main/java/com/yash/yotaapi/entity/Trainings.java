package com.yash.yotaapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
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
	
	private String trainingName;
	
	private String assignTo;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date startDate;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date endDate;

	private String status;

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
