package com.yash.yotaapi.entity;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "technology_management")
public class Technology {
	
	 	@Id
	    @Column(name = "tehnology_id")
	 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 	@SequenceGenerator(initialValue = 1, name = "id")
	    private Long id;
	 	
	    private String technology;
	    
	    private int countQuestion;
	    
	    private String action;

}
