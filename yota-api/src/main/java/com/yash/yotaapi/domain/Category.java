package com.yash.yotaapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(initialValue = 1, name = "id")
	private Long category_id;
	
	@NotBlank(message = "Parent Category Name should not be empty!")
	@Column(unique = true)
	private String name;

	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy/mm/dd")
	private Date createdAt;
	
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy/mm/dd")
	private Date updatedAt;
	
	@ManyToOne( fetch=FetchType.EAGER)
	@JoinColumn(name="technology_id")
	private TechnologyMaster technologyMaster;
}