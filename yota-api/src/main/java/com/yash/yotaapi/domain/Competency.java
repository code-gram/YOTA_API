package com.yash.yotaapi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This Competency Management model will work as a data transfer object.
 * Field validation will be performed here using jpa annotations.
 * 
 * @author gaurav.patil
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "competency_masters")
@Entity
@Setter
@Getter
@ToString
public class Competency {

	/**
	 * Id of competency for unique identification.This is going to be Primary
	 * Key.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * It represents name of competency.
	 */
	@NotEmpty(message ="Competency Name is mandatory")
	private String name;

	/**
	 * It represents name of competency manager.
	 */
	@NotEmpty(message ="Competency Manager is mandatory")
	private String competencyManager;
}
