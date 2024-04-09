package com.yash.yotaapi.controller;

import com.yash.yotaapi.domain.UserRole;
import com.yash.yotaapi.domain.YotaUser;
import com.yash.yotaapi.repository.YotaUserRepository;
import com.yash.yotaapi.security.jwt.JwtAuthRequest;
import com.yash.yotaapi.security.jwt.JwtAuthResponse;
import com.yash.yotaapi.service.IAuthService;
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
    private YotaUserRepository yotaUserRepository;

    @PostMapping(value = "/register")
    public String registerYotaUser(@RequestBody YotaUser yotaUser) {
        yotaUser.setRole(new UserRole());
        yotaUser.getRole().setId(4);
        yotaUserRepository.save(yotaUser);
        return "New User Registered Successfully";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtAuthResponse> authenticate(@RequestBody JwtAuthRequest authenticationRequest) {
        JwtAuthResponse authResponse = this.authService.login(authenticationRequest);
        return ResponseEntity.ok(authResponse);
    }
}
