package com.yash.yotaapi.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	
	 @JsonProperty("Question")
	 public String question;
	 @JsonProperty("Answer") 
	 public String answer;
	 
	 private String level;
	 private String technologyId;
	 private String clientId;
	 private String clientQuestion;
}
