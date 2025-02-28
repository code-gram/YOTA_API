package com.yash.yotaapi.controllers;

import com.yash.yotaapi.dto.PasswordDto;
import com.yash.yotaapi.dto.YotaUserDto;
import com.yash.yotaapi.security.jwt.JwtAuthRequest;
import com.yash.yotaapi.security.jwt.JwtAuthResponse;
import com.yash.yotaapi.services.IServices.IAuthService;
import com.yash.yotaapi.services.IServices.IYOTAUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yash.yotaapi.exceptions.ApplicationException;
import com.yash.yotaapi.util.ValidationUtility;

/**
 * Project Name - YOTASecurityAPI
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yash.raj
 * @since - 02-04-2024
 */
@RestController
public class LoginSignUpController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private ValidationUtility validationUtility;

    @Autowired
    private IYOTAUserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest authRequest) {

        if(!validationUtility.validateEmail(authRequest.getEmail())) {
            throw new ApplicationException("Email must contain @ and end with @yash.com.");
        }

        JwtAuthResponse authResponse = this
                .authService
                .login(authRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody YotaUserDto userDto) {
        String newUser = this
                .userService
                .createNewUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    @GetMapping("/get/user")
    public ResponseEntity<YotaUserDto> getUserByEmail(@RequestParam String email) {
        YotaUserDto user = this
                .userService
                .getUserByEmailAdd(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody PasswordDto passwordDto){
        String newPassword = this
                .userService
                .resetPassword(passwordDto);
        return new ResponseEntity<>(newPassword, HttpStatus.CREATED);
    }
}
