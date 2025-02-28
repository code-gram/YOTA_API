package com.yash.yotaapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yash.yotaapi.dto.TPRDto;
import lombok.*;
import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	private String status;

	private int totalNominations;

	private int registeredInTraining;

	@ManyToMany
	private List<YotaUser> assign;

	@Transient
	private List<TPRDto> assignTest;

	@ManyToMany
	@JoinTable(name = "training_test_assign",
			joinColumns = @JoinColumn(name = "training_id"),
			inverseJoinColumns = @JoinColumn(name = "test_id"))
	private List<Tests> tests;

	//SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

//	public void setStartDate(String startDate) {
//		try {
//			this.startDate = dateFormat.parse(startDate);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void setEndDate(String endDate) {
//		try {
//			this.endDate = dateFormat.parse(endDate);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	}
}