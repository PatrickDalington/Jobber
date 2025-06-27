package com.cwp.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final long EXPIRATION_TIME = 86400000; // 1 DAY
	
	public String genrateToken(String email, String role) {
		
		return Jwts.builder()
				.setSubject(email)
				.claim("role", role)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(key)
				.compact();
				
	}
	
	public String extractEmail(String token) {
		return parseToken(token).getBody().getSubject();
	}
	
	public String extractRole(String token) {
		return parseToken(token).getBody().get("role", String.class);
	}
	
	public boolean isTokenValid(String token) {
		try {
			parseToken(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}
	
	private Jws<Claims> parseToken(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token);
	}

}
