package com.yash.yotaapi.domain;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This QuestionPaper Model Will Work As a Actual Object Of Assign Test
 * @author himanshu.pednekar
 *
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class QuestionPaper {
	
	/**
	 * ID Is Primary Key And It Is Generated Automatically
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long testId;
	
	/**
	 * TestName Should Not Be Empty
	 * 
	 */
	@NotBlank(message = "Test name is manadatory")
	private  String testName;
	
	/**
	 * TestDescription Should Not Be Empty
	 * 
	 */
	@NotBlank(message="Test description is mandatory")
	private String testDescription;
	
	/**
	 *  
	 *  CreateDate Of The QuestionPaper, It Will Be Automatically Generated At The
	 *  Time Of Record Creation. You Don't Need To Add The Created Date
	 * Manually From Client End  
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date createdAt;
	/**
	 * UpatedDate Of The QuestionPaper, It Will Be Automatically Generated
	 * At The Time Of Record Updation. You Don't Need To Add The Updated Date
	 * Manually From Client End     
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date updatedAt;

	
	/**
	 *This method will be called before the entity is inserted (persisted)
	 * into the database.
	 */
	@PrePersist
	public void onCreate() {
	this.createdAt = new Date();
	}
	
	
	/**
	 * This method will be called before the entity is updated in the database.
	 */
	@PreUpdate
	public void onUpdate() {
    this.updatedAt = new Date();
}
}

