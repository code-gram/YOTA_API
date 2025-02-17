package com.yash.yotaapi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDto {

	List<YotaUserDto> content;
	private Integer pageNumber;
	private Integer pageSize;
	private Integer totalPages;
}
