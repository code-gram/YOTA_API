package com.yash.yotaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TechnologyDto {
	
	private Long id;
 	
    private String technology;
    
    private Long countQuestion;
    
    private String action;

}
