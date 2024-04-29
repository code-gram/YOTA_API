package com.yash.yotaapi.entity;

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
@Table(name = "training")
public class Trainings {

	@Id
    @Column(name = "id")
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	@SequenceGenerator(initialValue = 1, name = "id")
	private Long id;
	
	private String trainingName;
	
	private String assignTo;

	private Date startDate;
	
	private Date endDate;
	
	private int totalNominations;
	
	private int registeredInTraining;

	@ManyToMany
	private List<YotaUser> assign;
}
