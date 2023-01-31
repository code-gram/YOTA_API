package com.yash.yotaapi.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
 * This Associate details model will work as a actual object of associates.
 * @author nitin.chougale
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AssociateDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
		
	@NotBlank(message = "First_Name should not be empty.")
	private String firstName;
		
	private String middleName;
		
	@NotBlank(message = "Last_Name should not be empty.")
	private String lastName;
	
	@Column(unique = true)
	@Email
	@NotBlank(message = "Email_Id should not be empty")
	private String emailId;
	
	@NotBlank(message = "Password should not be empty")
	@Size(min = 8, max = 12, message = "Password should be minimum of 8 and maximum of 12 digits.")
	private String password;
	
	
	@Size(min = 10,max = 10, message = "Contact_No should be exactly 10 characters.")
	private String contactNo;
	
	/**
	 * createDate of the AssociateDetails, It will be automatically generated at the
	 * time of record creation. You don't need to add the created date manually from
	 * client end
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date createdAt;

	/**
	 * upatedDate of the AssociateDetails, It will be automatically generated at the
	 * time of record updation. You don't need to add the updated date manually from
	 * client end
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
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

