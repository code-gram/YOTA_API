package com.yash.yotaapi.controller;

import com.yash.yotaapi.domain.UserRole;
import com.yash.yotaapi.domain.YotaUser;
import com.yash.yotaapi.model.request.AuthenticationRequest;
import com.yash.yotaapi.model.response.AuthenticationResponse;
import com.yash.yotaapi.repository.YotaUserRepository; 
import com.yash.yotaapi.security.JWTService;
import com.yash.yotaapi.security.YotaUserDetailsService;
import com.yash.yotaapi.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin("*")

@RestController

@RequestMapping("/users")

public
 
class UserAuthController {

    private AuthenticationManager authenticationManager;
    private YotaUserDetailsService yotaUserDetailsService;
    private JWTService jwtService;
    private UserAuthService userAuthService;
    private PasswordEncoder passwordEncoder;
    private YotaUserRepository yotaUserRepository; // Assuming a repository exists

    @Autowired
    public UserAuthController(AuthenticationManager authenticationManager,
                              YotaUserDetailsService yotaUserDetailsService,
                              JWTService jwtService,
                              UserAuthService userAuthService,
                              PasswordEncoder passwordEncoder,
                              YotaUserRepository yotaUserRepository) {
        this.authenticationManager = authenticationManager;
        this.yotaUserDetailsService = yotaUserDetailsService;
        this.jwtService = jwtService;
        this.userAuthService = userAuthService;
        this.passwordEncoder = passwordEncoder;
        this.yotaUserRepository = yotaUserRepository; 
    }

    @PostMapping(value = "/register")
    public String registerYotaUser(@RequestBody YotaUser yotaUser) {
        // Validate user details here
        yotaUser.setPassword(passwordEncoder.encode(yotaUser.getPassword())); // Encode password
        yotaUser.setRole(new UserRole());
        yotaUser.getRole().setId(4);
        yotaUserRepository.save(yotaUser); 
        return "New User Registered Successfully";
        
    }

    @PostMapping("/authenticate")    
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest)
    {
        try {
            authenticationManager.authenticate(     
            		new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw 
            	new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad Credentials");
        }
        UserDetails userDetails = yotaUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtService.generateToken(userDetails);
        String userRole = userAuthService.findUserRoleByUserName(authenticationRequest.getUsername());
        return AuthenticationResponse.builder()
                .authToken("Bearer " + token)
                .userRole(userRole)
                .build();
    }
}