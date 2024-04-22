package com.yash.yotaapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yash.yotaapi.constants.QuestionLevelTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * DTO for {@link com.yash.yotaapi.entity.Questions}
 * <p>
 * Project Name - YOTA_NEW
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yashr
 * @since - 22-04-2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionsDto {

    private Long id;

    private String questionTitle;

    private String correctAnswer;

    private String option_A;

    private String option_B;

    private String option_C;

    private String option_D;

    private QuestionLevelTypes questionLevel;

    @JsonIgnore
    private CategoryDto category;

    private Date created_At;

    private Date updated_At;
}
