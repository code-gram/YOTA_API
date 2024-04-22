package com.yash.yotaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyDto {
	
	private Long id;
 	
    private String technology;
    
    private Long countQuestion;
    
    private String action;

}
