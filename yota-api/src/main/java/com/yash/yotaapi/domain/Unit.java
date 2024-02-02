package com.yash.yotaapi.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This Parent Unit model will work as a data transfer object. Field
 * validation will be performed here using jpa annotations.
 * 
 *@author chandana.nemade
 *
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class Unit {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(initialValue = 1, name = "id")
	private Long id;

	/**
	 * name of the ParentUnit, Name should not be empty
	 */
	@NotBlank(message = "Parent Unit Name should not be empty!")
	@Column(unique = true)
	private String name;

	/**
	 * shortDescription of the ParentUnit, should not be empty
	 */
	@NotBlank(message = "Description is required.")
	private String shortDescription;
	
	
	private boolean status;

	/**
	 * createDate of the ParentTechnology, It will be automatically generated at the
	 * time of record creation. You don't need to add the created date manually from
	 * client end
	 */
	
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy/mm/dd")
	private Date createdAt;

	/**
	 * upatedDate of the ParentTechnology, It will be automatically generated at the
	 * time of record updation. You don't need to add the updated date manually from
	 * client end
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy/mm/dd")
	private Date updatedAt;

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
