package com.yash.yotaapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project Name - YOTASecurityAPI
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - amar s
 * @since - 20-05-2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTestAnswerDto {

    private Long id;

    private Long testid;

    private String userEmailId;

    private Long questionId;

    private String selectedOption;
}