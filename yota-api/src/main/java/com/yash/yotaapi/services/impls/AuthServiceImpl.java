package com.yash.yotaapi.services.impls;

import com.yash.yotaapi.constants.AppConstants;
import com.yash.yotaapi.constants.RequestStatusTypes;
import com.yash.yotaapi.constants.UserAccountStatusTypes;
import com.yash.yotaapi.exceptions.ApplicationException;
import com.yash.yotaapi.security.jwt.JwtAuthRequest;
import com.yash.yotaapi.security.jwt.JwtAuthResponse;
import com.yash.yotaapi.security.jwt.JwtTokenHelper;
import com.yash.yotaapi.security.userdetails.CustomUserDetails;
import com.yash.yotaapi.security.userdetails.CustomUserDetailsServiceImpl;
import com.yash.yotaapi.services.IServices.IAuthService;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * Project Name - YOTASecurityPOC
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yashr
 * @since - 02-04-2024
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

        if (StringUtils.equalsIgnoreCase(userDetails.getAccountStatus().toString(),
                UserAccountStatusTypes.APPROVED.toString())) {

            token = this
                    .tokenHelper
                    .generateToken(userDetails);

            authResponse.setToken(token);
            authResponse.setMessage("Successfully Logged In");
            authResponse.setStatus(RequestStatusTypes.SUCCESS.toString());

        } else if (StringUtils.equalsIgnoreCase(userDetails.getAccountStatus().toString(),
                UserAccountStatusTypes.DECLINED.toString())) {

            authResponse.setToken(null);
            authResponse.setStatus(RequestStatusTypes.FAILED.toString());
            authResponse.setMessage(AppConstants.DECLINED_USER_MESSAGE);
        } else {
            authResponse.setToken(null);
            authResponse.setStatus(RequestStatusTypes.FAILED.toString());
            authResponse.setMessage("Your request is in Pending state for Approval");
        }
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
