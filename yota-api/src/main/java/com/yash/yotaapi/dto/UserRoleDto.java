package com.yash.yotaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project Name - YOTASecurityAPI
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yash.raj
 * @since - 02-04-2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {

    private Long roleId;

    private String roleTypes;
}
