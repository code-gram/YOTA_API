package com.yash.yotaapi.controller;

import com.yash.yotaapi.domain.YotaUser;
import com.yash.yotaapi.security.jwt.JwtAuthRequest;
import com.yash.yotaapi.security.jwt.JwtAuthResponse;
import com.yash.yotaapi.service.IAuthService;
import com.yash.yotaapi.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserAuthController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping(value = "/register")
    public ResponseEntity<String> registerYotaUser(@RequestBody YotaUser yotaUser) {
        String message = this.userAuthService.registerUser(yotaUser);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtAuthResponse> authenticate(@RequestBody JwtAuthRequest authenticationRequest) {
        JwtAuthResponse authResponse = this.authService.login(authenticationRequest);
        return ResponseEntity.ok(authResponse);
    }
}
