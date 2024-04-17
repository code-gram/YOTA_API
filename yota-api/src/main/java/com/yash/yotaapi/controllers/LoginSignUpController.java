package com.yash.yotaapi.controllers;

import com.yash.yotaapi.dto.YotaUserDto;
import com.yash.yotaapi.security.jwt.JwtAuthRequest;
import com.yash.yotaapi.security.jwt.JwtAuthResponse;
import com.yash.yotaapi.services.IServices.IAuthService;
import com.yash.yotaapi.services.IServices.IYOTAUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private IYOTAUserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest authRequest) {
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
}
