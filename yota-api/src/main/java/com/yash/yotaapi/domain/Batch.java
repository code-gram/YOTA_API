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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

/*
 * This batch model work as a data transfer object, Field validation will be
 * performed here using jpa annotation.
 * @author anil.shimpi
 */

public class Batch {

	/* BatchId should be primary key and it not be null. */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long batchId;
	
	/* batchName should not be empty or blank */
	@NotEmpty(message = "Batch name is manadatory")
	private String batchName;
	
	/* batchDescription should not be empty or blank */
	@NotEmpty(message="Batch description is mandatory")
	private String batchDescription;
	
	/* Start date should not be null and yyyy-MM-dd format */
	@NotNull(message = "Start date can not be empty or blank")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	/* End date should be yyyy-MM-dd format */
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	/*
	 * CreatedDate is automatically generated at the time of create, No need to set
	 * up manually.
	 */
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date createdAt =new Date();
	
	
	/*
	 * UpdatedDate is automatically generated at the time of updated, No need to set
	 * up manually.
	 */

	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date updatedAt =new Date();
	
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
