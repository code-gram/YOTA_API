package com.yash.yotaapi.security;
 
import com.yash.yotaapi.domain.YotaUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
 
 
import java.security.Key;
import java.util.*;
 
@Service
public class JWTServiceImpl implements JWTService {
	@Value("${token.signing.key}")
	private String signingKey;
 
	@Override
	public String extractUserName(String token) {
		Claims claims = Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token).getBody();
		String userName = claims.getSubject();
		return userName;
	}
 
	@Override
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}
 
	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		 try{
	            Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
	            return true;
	        }catch (SignatureException ex){
	            System.out.println("Invalid JWT Signature");
	        }catch (MalformedJwtException ex){
	            System.out.println("Invalid JWT Token : "+ex);
	        }catch (ExpiredJwtException ex){
	            System.out.println("Expired JWT token");
	        }catch (UnsupportedJwtException ex){
	            System.out.println("Unsupported JWT token");
	        }catch (IllegalArgumentException ex){
	            System.out.println("JWT claims string is empty");
	        }
	System.out.println("isTokenValid:"+ Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token));
	        return false;
 
	}
 
	private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 600 * 24))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}
 
	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(signingKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
 
}