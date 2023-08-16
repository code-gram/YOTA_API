package com.yash.yotaapi.springSecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTTokenHelper 
{
 private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; //we have mention token expire time
 private String SECRET_KEY = "secret";

  public String getUserNameFromToken(String token) 
  {
    return getClaimFronToken(token, Claims::getSubject);
  }

  public Date getExpirationDateFromToken(String token) 
  {
     return getClaimFronToken(token, Claims::getExpiration);
  }

  public <T> T getClaimFronToken(String token, Function<Claims, T> claimsResolver) 
  {
     final Claims claims = getAllClaimsFromToekn(token);
     return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToekn(String token) 
  {
     return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) 
  {
     return getExpirationDateFromToken(token).before(new Date());
  }

  public String generateToken(UserDetails userDetails) 
  {
     Map<String, Object> claims = new HashMap<>();
     return createToken(claims, userDetails.getUsername());
  }

  private String createToken(Map<String, Object> claims, String subject) 
  {
     return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
           .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
           .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
  }

  public Boolean validateToken(String token, UserDetails userDetails) 
  {
     final String username = getUserNameFromToken(token);
     return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
}
