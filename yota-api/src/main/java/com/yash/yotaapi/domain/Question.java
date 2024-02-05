package com.yash.yotaapi.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This Question Management model will work as a data transfer object. Field
 * validation will be performed here using jpa annotations.
 * 
 * @author priya.m
 *
 */
@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Question should not be empty
	 */
	@NotBlank(message = "Question is required")

	// my change
	@Column(columnDefinition = "TEXT")
	private String question;

	/**
	 * Question level of the Question should not be empty
	 */
	private String questionLevel;

	/**
	 * Option should not be empty
	 */
	@NotBlank(message = "Option A is required")
	private String option_A;

	/**
	 * Option should not be empty
	 */
	@NotBlank(message = "Option B is required")
	private String option_B;

	/**
	 * Option should not be empty
	 */
	@NotBlank(message = "Option C is required")
	private String option_C;

	/**
	 * Option should not be empty
	 */
	@NotBlank(message = "Option D is required")
	private String option_D;

	/**
	 * Answer of the Question should not be empty
	 */
	@NotBlank(message = "Answer is required")
	private String correctAnswer;

	/**
	 * createDate of the Question Bank, It will be automatically generated at the
	 * time of record creation. You don't need to add the created date manually from
	 * client end
	 */

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date created_At;

	/**
	 * upatedDate of the Question Bank, It will be automatically generated at the
	 * time of record updation. You don't need to add the updated date manually from
	 * client end
	 */

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updated_At;

	public static void setTechnology(Object technology) {
	}

	/**
	 * This method will be called before the entity is inserted (persisted) into the
	 * database.
	 */
	@PrePersist
	public void onCreate() {
		this.created_At = new Date();
	}

	/**
	 * This method will be called before the entity is updated in the database.
	 */
	@PreUpdate
	public void onUpdate() {
		this.updated_At = new Date();
	}

	private Long technologyId;

	public Object getTechnology() {
		return null;
	}

	@ManyToOne
	@JoinColumn(name = "test_id", nullable = false)
	private Test test;

	// @Transient
	// public List<TechnologyMaster> technologyMasters;

//	public String getQuestion() {
//		return question;
//	}

}
