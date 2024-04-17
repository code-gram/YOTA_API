package com.yash.yotaapi.services.IServices;

import com.yash.yotaapi.security.jwt.JwtAuthRequest;
import com.yash.yotaapi.security.jwt.JwtAuthResponse;

/**
 * Project Name - YOTASecurityPOC
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yashr
 * @since - 02-04-2024
 */
public interface IAuthService {

    JwtAuthResponse login(JwtAuthRequest authRequest);
}
