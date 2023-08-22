/*
 * package com.yash.yotaapi.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.authentication.BadCredentialsException; import
 * org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.yash.yotaapi.springSecurity.JWTAuthResponse; import
 * com.yash.yotaapi.springSecurity.JWTTokenHelper; import
 * com.yash.yotaapi.springSecurity.JwtAuthRequest;
 * 
 * 
 * 
 * @RestController
 * 
 * @RequestMapping("/api/v1/auth/") public class AuthController {
 * 
 * @Autowired private JWTTokenHelper jwtTokenHelper;
 * 
 * @Autowired private UserDetailsService userDetailsService;
 * 
 * @Autowired private AuthenticationManager authenticationManager;
 * 
 * @PostMapping("/login") public ResponseEntity<JWTAuthResponse>
 * createToken(@RequestBody JwtAuthRequest request) throws Exception {
 * this.authenticate(request.getUsername(), request.getPassword()); UserDetails
 * userDetails =
 * this.userDetailsService.loadUserByUsername(request.getUsername()); String
 * token = this.jwtTokenHelper.generateToken(userDetails);
 * 
 * JWTAuthResponse response = new JWTAuthResponse(); response.setToken(token);
 * return new ResponseEntity<JWTAuthResponse>(response, HttpStatus.OK);
 * 
 * }
 * 
 * private void authenticate(String username, String password) throws Exception
 * { UsernamePasswordAuthenticationToken authenticationToken = new
 * UsernamePasswordAuthenticationToken(username, password); try {
 * this.authenticationManager.authenticate(authenticationToken); }
 * catch(BadCredentialsException e) {
 * System.out.println("Invalid Credentials !!"); throw new
 * Exception("Invalid username or password"); } } }
 */