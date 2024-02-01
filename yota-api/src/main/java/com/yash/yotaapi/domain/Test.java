package com.yash.yotaapi.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This Test Management model will work as a data transfer object.
 * Field validation will be performed here using jpa annotations.
 * 
 * @author gaurav.patil
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "test_masters")
@Entity
@Setter
@Getter
@ToString
public class Test {

	/**
	 * Id of test for unique identification.This is going to be Primary
	 * Key.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * It represents name of test.
	 */
	@NotEmpty(message ="Test Name is mandatory")
	private String name;

	/**
	 * It represents description of test.
	 */
	private String description;
	
	
	//@OneToMany(cascade = CascadeType.ALL)
    //private List<Question> questions;

}
