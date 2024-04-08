package com.yash.yotaapi.domain;

import java.io.File;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "associate_master")
public class NewAssociateDetail {
	

	/**
	 * ID is a primary key and it is generated automatically.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * Employee ID of associate.
	 */
	@NotNull
	private Long employeeId;
		
	/**
	 * Employee Name of associate.
	 */
	@NotNull
	private String employeeName;
	
	/**
	 * Employee Email_ID of associate.
	 */
	@NotNull
	private String employeeEmailId;
	
	/**
	 * Employe Password of associate.
	 */
	@NotNull
	private String employeePassword;
		
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="training_id")
	private Training training;
}
