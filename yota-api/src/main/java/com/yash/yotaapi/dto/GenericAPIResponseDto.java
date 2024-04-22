package com.yash.yotaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project Name - YOTA_NEW
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yashr
 * @since - 23-04-2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenericAPIResponseDto {

    private Object data;

    private String message;

    private String status;
}
