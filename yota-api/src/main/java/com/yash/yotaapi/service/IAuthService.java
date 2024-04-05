package com.yash.yotaapi.service;

import com.yash.yotaapi.security.jwt.JwtAuthRequest;
import com.yash.yotaapi.security.jwt.JwtAuthResponse;

/**
 * Project Name - YOTA_API
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yashr
 * @since - 05-04-2024
 */
public interface IAuthService {

    JwtAuthResponse login(JwtAuthRequest authRequest);
}
