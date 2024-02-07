package com.yash.yotaapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = { "questions", "associates" })
@ToString(exclude = { "questions", "associates" })
@Entity
@Table(name = "test_masters")
public class Test {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Test Name is mandatory")
	private String name;

	private String description;

	@NotNull(message = "Start date cannot be Null")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date startDate;

	@NotNull(message = "End date cannot be null")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date endDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@CreatedDate
	private Date createdAt;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@LastModifiedDate
	private Date updatedAt;

	@OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonIgnore
	private Set<Question> questions = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "test_id")
	private Set<AssociateDetails> associates = new HashSet<>();

	@PrePersist
	public void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	public void onUpdate() {
		this.updatedAt = new Date();
	}
}