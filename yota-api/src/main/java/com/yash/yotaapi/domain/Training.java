package com.yash.yotaapi.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This Training Management model will work as a data transfer object.
 * Field validation will be performed here using jpa annotations.
 *
 * @author pravin.navarkar
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Training_Masters")
public class Training {

	/**
	 * id of Training master for unique identification.This is going to be Primary Key.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * It represents name of training master and checks whether name is empty or null.
	 */
	@NotEmpty
	private String name;
}
