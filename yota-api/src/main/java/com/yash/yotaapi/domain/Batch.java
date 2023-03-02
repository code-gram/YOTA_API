package com.yash.yotaapi.domain;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yash.yotaapi.constraints.CompareDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


/*
 * This batch model work as a data transfer object, Field validation will be
 * performed here using jpa annotation.
 * @author anil.shimpi
 */


/*
 * SQLDelete annotation overrode delete command to updated command in project,
 * when we execute delete command it turned into update command and set deleted
 * field value is true.
 */
@SQLDelete(sql="UPDATE BATCH_MANAGEMENT SET Status = true WHERE id=?") 
@Table(name = "Batch_Management")
@Entity
//@CompareDate(before = "startDate",after="endDate")
public class Batch {

	/* BatchId should be primary key and it not be null. */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
	
	/* batchIdentifier should not be empty and it not be null */
	@NotEmpty(message = "Batch Identifier is manadatory")
	@Column(unique=true,nullable = false,updatable = false)
	@Size(min=3,max=12 ,message = "Batch identifer name should be inbetween 3 to 12 character only")
	private String batchIdentifier;
	
	/* batchName should not be empty or blank */
	@NotEmpty(message = "Batch name is manadatory")
	@Column(unique=true,nullable = false)
	private String batchName;
	
	/* batchDescription should not be empty or blank */
	@NotBlank(message="Batch description is mandatory")
	private String batchDescription;
	
	/* Start date should not be null and yyyy-MM-dd format */
	@NotNull(message = "Start date can not be Null")
	@JsonFormat(pattern="yyyy/MM/dd")
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name="start_date")
	//@CompareDate(before = "startDate",after="endDate", message="EndDate must be greater than startDate ")
	private Date startDate;
	
	/* End date should be yyyy-MM-dd format */
	@JsonFormat(pattern="yyyy/MM/dd")
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name="end_date")
	@NotNull(message = "EndDate not be null")
	//@CompareDate(before = "startDate",after="endDate", message="EndDate must be greater than startDate ")
	private Date endDate;
	
	/*
	 * CreatedDate is automatically generated at the time of create, No need to set
	 * up manually.
	 */
	@JsonFormat(pattern="yyyy/MM/dd")
	@DateTimeFormat(iso = ISO.DATE)
	@CreatedDate
	private Date createdAt;
	
	
	/*
	 * UpdatedDate is automatically generated at the time of updated, No need to set
	 * up manually.
	 */

	@JsonFormat(pattern="yyyy/MM/dd")
	@DateTimeFormat(iso = ISO.DATE)
	@LastModifiedDate
	private Date updatedAt;
	

	/* set boolean flag for soft delete */
	private boolean status=Boolean.FALSE;
	
	
	/**
	 * This method will be called before the entity is inserted (persisted) into the
	 * database.
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
