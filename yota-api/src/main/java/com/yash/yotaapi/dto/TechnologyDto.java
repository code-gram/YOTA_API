package com.yash.yotaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TechnologyDto {

    private Long id;

    private String technology;

    private Integer questionCountUnderTechnology = 0;

    private List<CategoryDto> categories;
}
