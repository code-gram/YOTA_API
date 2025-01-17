package com.yash.yotaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yash.yotaapi.constants.QuestionLevelTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionlistDto {
    private Long id;

    private String questionTitle;

    private String correctAnswer;

    private String option_A;

    private String option_B;

    private String option_C;

    private String option_D;

    private QuestionLevelTypes questionLevel;


    private CategoryNameDto category;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date created_At;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date updated_At;

}
