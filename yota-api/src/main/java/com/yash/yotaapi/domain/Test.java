package com.yash.yotaapi.domain;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This Test model will work as a data transfer object. 
 * Field validation will be performed here using jpa annotations.
 * 
 * @author Dimpal Gaur
 *
 */
@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {

	/**
	 * id column is work as a Primary key for test tabel
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * technologyId work as a foreign key for test table
	 */
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "technologyId", referencedColumnName = "id")
	private TechnologyMaster technologyMaster;
	
	/**
	 * title should not be empty
	 */
	@NotBlank(message = "title is required")
	@Column(unique = true)
	private String title;
	

	/**
	 * Description should not be empty
	 */
	@Column(columnDefinition="TEXT")                             
	@NotBlank(message = "Description is required")
	private String description;

	/**
	 * Instruction should not be empty
	 */
	@Column(columnDefinition="TEXT")   
	@NotBlank(message = "Test Instruction is required")
	private String instruction; 

	/**
	 * createDate of the Test, It will be automatically generated at the
	 * time of record creation. You don't need to add the created date manually from
	 * client end
	 */

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date created_At;

	/**
	 * upatedDate of the Test, It will be automatically generated at the
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
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

}
