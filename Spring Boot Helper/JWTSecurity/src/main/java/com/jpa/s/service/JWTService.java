package com.jpa.s.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JWTService {

	public static final String SECRET = "3373367638792F423F4528482B4D6251655468576D5A7134743777217A244326";
	
	
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);   // see again if not work
	}
	
	 public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }
	 private Claims extractAllClaims(String token) {
	        return Jwts
	                .parserBuilder()
	                .setSigningKey(getSignKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }
	 
	 
	 private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());   // see again
	    }
	 
	 
	  public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }


	public String generateToken(String id) {
		Map<String,Object>claims=new HashMap<>();
		return createToken(claims,id);
	}

	private String createToken(Map<String, Object> claims, String id) {
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(id)
				.setIssuedAt(new java.util.Date (System.currentTimeMillis()))
				.setExpiration(new java.util.Date(System.currentTimeMillis()+1000*60*30))
				.signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
	}
	
	private Key getSignKey() {
		byte[] keyBytes=Decoders.BASE64.decode(SECRET);
		
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
