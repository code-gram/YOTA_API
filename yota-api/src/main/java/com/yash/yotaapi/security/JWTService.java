package com.yash.yotaapi.security;


import com.yash.yotaapi.domain.YotaUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String extractUserName(String token);

    String generateToken(UserDetails yotaUser);

    boolean isTokenValid(String token, UserDetails userDetails);
}
