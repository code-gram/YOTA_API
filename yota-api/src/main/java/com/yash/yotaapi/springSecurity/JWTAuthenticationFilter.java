/*
 * package com.yash.yotaapi.springSecurity;
 * 
 * import java.io.IOException;
 * 
 * import javax.servlet.FilterChain; import javax.servlet.ServletException;
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.context.SecurityContextHolder; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.web.authentication.
 * WebAuthenticationDetailsSource; import
 * org.springframework.stereotype.Component; import
 * org.springframework.web.filter.OncePerRequestFilter;
 * 
 * import io.jsonwebtoken.ExpiredJwtException; import
 * io.jsonwebtoken.MalformedJwtException;
 * 
 * @Component public class JWTAuthenticationFilter extends OncePerRequestFilter
 * {
 * 
 * @Autowired private UserDetailsService userDetailsService;
 * 
 * @Autowired private JWTTokenHelper jwtTokenHelper;
 * 
 * @Override protected void doFilterInternal(HttpServletRequest request,
 * HttpServletResponse response, FilterChain filterChain) throws
 * ServletException, IOException { // 1. get token
 * 
 * String requestToken = request.getHeader("Authorization");
 * 
 * //BearerToken 223532sdgsg -. Bearer looks like this
 * 
 * System.out.println(requestToken);
 * 
 * // in below code we are extracting details ((header, payload, key) from
 * generated token
 * 
 * String username = null;
 * 
 * String token = null;
 * 
 * if(requestToken != null && requestToken.startsWith("Bearer")) { //if this if
 * condition is satisfied means our token is good
 * 
 * token = requestToken.substring(7); //extracting actual token from whole
 * bearer token
 * 
 * try { username = this.jwtTokenHelper.getUserNameFromToken(token); }
 * catch(IllegalArgumentException e) {
 * System.out.println("unable to get Jwt token"); } catch (ExpiredJwtException
 * e) { System.out.println("Jwt token has Expired"); } catch
 * (MalformedJwtException e) { System.out.println("invalid jwt"); } } else {
 * System.out.println("JWT token does not begin with Bearer"); }
 * 
 * // Once we get the token , now validate
 * 
 * if(username != null &&
 * SecurityContextHolder.getContext().getAuthentication()==null) { UserDetails
 * userDetails = this.userDetailsService.loadUserByUsername(username);
 * if(this.jwtTokenHelper.validateToken(token, userDetails)) { // sahi chal rha
 * hai // ab authenticate karna hai UsernamePasswordAuthenticationToken
 * usernamePasswordAuthenticationToken = new
 * UsernamePasswordAuthenticationToken(userDetails, null,
 * userDetails.getAuthorities());
 * usernamePasswordAuthenticationToken.setDetails(new
 * WebAuthenticationDetailsSource().buildDetails(request));
 * SecurityContextHolder.getContext().setAuthentication(
 * usernamePasswordAuthenticationToken); } else {
 * System.out.println("Invalid jwt token"); } } else {
 * System.out.println("username is null or context is not null"); }
 * filterChain.doFilter(request, response); }
 * 
 * }
 */