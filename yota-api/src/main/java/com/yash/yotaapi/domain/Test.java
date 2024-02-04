package com.yash.yotaapi.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	/** Start date of the test. */
    @NotNull(message = "Start date cannot be Null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date startDate;
 
    /** End date of the test. */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "End date cannot be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date endDate;
	
    /** Date when the test was created. */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @CreatedDate
    private Date createdAt;
 
    /** Date when the test was last updated. */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @LastModifiedDate
    private Date updatedAt;
    
    /** Set of questions associated with the test. */
    @OneToMany(mappedBy = "test",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Question> questions;
    
    /** Set of associates details associated with the test. */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "test_id")
    private Set<AssociateDetails> associates;
    
    /** Pre-persist action to set the created date. */
    @PrePersist
    public void onCreate() {
        this.createdAt = new Date();
    }
 
    /** Pre-update action to set the updated date. */
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = new Date();
    }

}
