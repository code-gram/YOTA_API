package com.yash.yotaapi.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project Name - YOTASecurityPOC
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yashr
 * @since - 02-04-2024
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthRequest {

    private String email;

    private String password;
}
