package com.yash.yotaapi.model.request;

import lombok.Data;

@Data
public class TestRequest {

		private Long id;
		
		private Long technologyId;
		
		private String title;

		private String description;

		private String instruction;

}
