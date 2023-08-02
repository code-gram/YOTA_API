package com.yash.yotaapi.domain;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This Test model will work as a data transfer object. Field
 * validation will be performed here using jpa annotations.
 *
 * @author jaie.arkadi
 *
 */
@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(initialValue = 1,name = "test_id")
	private Long testId;

	/**
	 * name of the test, Name should not be empty
	 */
	@NotBlank(message = "Test Name should not be empty!")
	@Column(unique = true)
	private String testName;

	/**
	 * Description of the test, should not be empty
	 */
	@NotBlank(message = "Description is required.")
	private String testDescription;

	/**
	 * technology of the test, many to one relationship
	 */
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="id", nullable=false)
    private ParentTechnology technology;

}
