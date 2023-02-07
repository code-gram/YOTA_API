package com.yash.yotaapi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
	 *  Question type of the Question should not be empty
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
	 * Option should not be empty
	 */
	@NotBlank(message = "Options is required")
	private String option1;
	
	/**
	 * Option should not be empty
	 */
	@NotBlank(message = "Options is required")
	private String option2;
	
	/**
	 * Option should not be empty
	 */
	@NotBlank(message = "Options is required")
	private String option3;
	
	/**
	 * Option should not be empty
	 */
	@NotBlank(message = "Options is required")
	private String option4;
	
	/**
	 * Answer of the Question should not be empty
	 */
	@NotBlank(message = "Answer is required")
	private String correctAnswer;
	
	
	/**
	 *  createDate of the Question Bank, It will be automatically generated at the
	 * time of record creation. You don't need to add the created date manually from
	 * client end
	 */
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date created_At;
	
	/**
	 * upatedDate of the Question Bank, It will be automatically generated at the
	 * time of record updation. You don't need to add the updated date manually from
	 * client end
	 */
	@JsonFormat(pattern="yyyy-MM-dd")
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
}
