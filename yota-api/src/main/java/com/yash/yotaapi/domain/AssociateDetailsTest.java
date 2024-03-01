package com.yash.yotaapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "associate_details_tests")
public class AssociateDetailsTest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "associate_details_id")
	private AssociateDetails associateDetails;
	
	@ManyToOne
	@JoinColumn(name = "test_id")
	private Test test;
	
	@Column(name = "test_status")
	private String testStatus;

}
