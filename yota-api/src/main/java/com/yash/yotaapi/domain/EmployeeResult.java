package com.yash.yotaapi.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "employee_result")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResult {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "id")
	private Integer id;
	
	@Column(name= "name")
	private String name;
	
	@Column(name = "class_name")
	private Integer className;
	
	@Column(name = "employee_section")
	private Character section;
	
	@Column(name = "employee_fees")
	private Integer fees ;
	
	@Column(name = "employee_deparment")
	private String employeeDeparment;
	
	@Column(name = "marks")
	private Integer marks ;
	
	
	}
