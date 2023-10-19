package com.yash.yotaapi.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
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
	 * Question type of the Question should not be empty
	 */

	@NotBlank(message = "Question type is required")
	private String questionType;

	/**
	 * Question level of the Question should not be empty
	 */
	@NotBlank(message = "Question level is required")
	private String questionLevel;

	/**
	 * Question should not be empty
	 */
	@NotBlank(message = "Question is required")
	private String question;

	/**
	 * Answer type should not be empty
	 */
	@NotBlank(message = "Answer type is required")
	private String answerType;

	/**
	 * Option should not be empty
	 */
	@NotBlank(message = "Option A is required")
	private String a;

	/**
	 * Option should not be empty
	 */
	@NotBlank(message = "Option B is required")
	private String b;

	/**
	 * Option should not be empty
	 */
	@NotBlank(message = "Option C is required")
	private String c;

	/**
	 * Option should not be empty
	 */
	@NotBlank(message = "Option D is required")
	private String d;

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
	
	private Long parentTechnology_id;
	
	@Transient
  public List<TechnologyMaster> technologyMasters;

}
