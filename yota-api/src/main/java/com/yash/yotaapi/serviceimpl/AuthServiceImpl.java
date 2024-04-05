package com.yash.yotaapi.serviceimpl;

import com.yash.yotaapi.exception.ApplicationException;
import com.yash.yotaapi.security.jwt.JwtAuthRequest;
import com.yash.yotaapi.security.jwt.JwtAuthResponse;
import com.yash.yotaapi.security.jwt.JwtTokenHelper;
import com.yash.yotaapi.security.userdetails.CustomUserDetails;
import com.yash.yotaapi.security.userdetails.CustomUserDetailsServiceImpl;
import com.yash.yotaapi.service.IAuthService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * Project Name - YOTA_API
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yashr
 * @since - 05-04-2024
 */
@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private JwtTokenHelper tokenHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Override
    public JwtAuthResponse login(JwtAuthRequest authRequest) {
        String userName = authRequest.getEmail();
        String password = authRequest.getPassword();
        String token = null;

        this.authenticate(userName, password);

        CustomUserDetails userDetails = this
                .userDetailsService
                .loadUserByUsername(userName);

        Assert.notNull(userDetails);
        JwtAuthResponse authResponse = new JwtAuthResponse();


        token = this
                .tokenHelper
                .generateToken(userDetails);

        authResponse.setToken(token);
        authResponse.setMessage("Successfully Logged In");

        return authResponse;
    }

    private void authenticate(String userName,
                              String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        try {
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException credentialsException) {
            throw new ApplicationException("Invalid username or password");
        }
    }
}
