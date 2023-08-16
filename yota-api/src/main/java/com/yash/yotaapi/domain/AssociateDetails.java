package com.yash.yotaapi.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.yash.yotaapi.util.Status;

import org.apache.catalina.startup.Tomcat.ExistingStandardWrapper;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Columns;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
	
	/**
	 * ID is a primary key and it is generated automatically.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
		
	/**
	 * First_Name of associate.
	 */
	private String firstName;
		
	/**
	 * Middle_Name of associate.
	 */
	private String middleName;
	
	/**
	 * last_Name of associate.
	 */
	private String lastName;
	
	/**
	 * Email_Id is necessary field and it should be unique for every associate.
	 */
	@Column(unique = true, nullable = false)
	@Email
	@NotNull(message = "Email_Id field is mandatory")
	private String emailId;
	
	/**
	 * Password should be min 8 & max 12 digits, and it should not be empty.
	 */
	@NotBlank(message = "Password is mandatory")
	@Size(min = 8, max = 12, message = "Password should be minimum of 8 and maximum of 12 digits.")
	private String password;
	
	/**
	 * Status field shows the status of associate, and its default value is active.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "Status", nullable = false)
	private Status status = Status.Active;

	
	/**
	 * Contact no should be exactly 10 digits.
	 */
	@Size(min = 10,max = 10, message = "Contact_No should be exactly 10 characters.")
	private String contactNo;
	
	/**
	 * createDate of the AssociateDetails, It will be automatically generated at the
	 * time of record creation. You don't need to add the created date manually from
	 * client end
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy/mm/dd")
	private Date createdAt;

	/**
	 * upatedDate of the AssociateDetails, It will be automatically generated at the
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

