package com.yash.yotaapi.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long questionId;
	
	private String clientId;
	
	private String technologyId;
	
	@NotBlank(message = "Question is required")
	private String clientQuestion;
	
	private String answer;
	
//	@JsonFormat(pattern = "yyyy-MM-dd")
//	private Date created_At;
	
	private String level;
	
	

}
