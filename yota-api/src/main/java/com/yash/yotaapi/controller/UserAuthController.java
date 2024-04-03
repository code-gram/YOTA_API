package com.yash.yotaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.domain.UserRole;
import com.yash.yotaapi.domain.YotaUser;
import com.yash.yotaapi.repository.YotaUserRepository;
//import com.yash.yotaapi.security.JWTService;
//import com.yash.yotaapi.security.YotaUserDetailsService;
import com.yash.yotaapi.service.UserAuthService;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserAuthController {

    private UserAuthService userAuthService;

    private YotaUserRepository yotaUserRepository; // Assuming a repository exists

    @Autowired
    public UserAuthController(                           
                              UserAuthService userAuthService,                            
                              YotaUserRepository yotaUserRepository) {
        this.userAuthService = userAuthService;
        this.yotaUserRepository = yotaUserRepository; 
    }

    @PostMapping(value = "/register")
    public String registerYotaUser(@RequestBody YotaUser yotaUser) {
        yotaUser.setRole(new UserRole());
        yotaUser.getRole().setId(4);
        yotaUserRepository.save(yotaUser); 
        return "New User Registered Successfully";
        
    }

//    @PostMapping("/authenticate")    
//    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest){
//        try {
//                authenticationManager.authenticate(     
//            		new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//            );
//        } catch (BadCredentialsException e) {
//            throw new UserNotFoundException(HttpStatus.UNAUTHORIZED, "Invalid username and password!!");
//       
//        }
//        UserDetails userDetails = yotaUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//        String token = jwtService.generateToken(userDetails);
//        String userRole = userAuthService.findUserRoleByUserName(authenticationRequest.getUsername());
//        return AuthenticationResponse.builder()
////                .authToken("Bearer " + token)
//                .userRole(userRole)
//                .build();
//    }
}