package com.yash.yotaapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "training_management")
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
}
