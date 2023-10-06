package com.yash.yotaapi.controller;

import com.yash.yotaapi.domain.YotaUser;
import com.yash.yotaapi.model.request.AuthenticationRequest;
import com.yash.yotaapi.model.response.AuthenticationResponse;
import com.yash.yotaapi.repository.YotaUserRepository;
import com.yash.yotaapi.security.JWTService;
import com.yash.yotaapi.security.YotaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/yota/user")
@RestController
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private YotaUserRepository yotaUserRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private YotaUserDetailsService yotaUserDetailsService;
    @Autowired
    private JWTService jwtService;

    @PostMapping(value = "/register")
    public String registerYotaUser(@RequestBody YotaUser yotaUser){
        yotaUser.setPassword(passwordEncoder.encode(yotaUser.getPassword()));
        yotaUserRepository.save(yotaUser);
        return "New User Registered Successfully";
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("Bad Credentials");
        }
        UserDetails userDetails = yotaUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtService.generateToken(userDetails);
        AuthenticationResponse response = AuthenticationResponse.builder().authToken(token).build();
        return response;
    }
}
