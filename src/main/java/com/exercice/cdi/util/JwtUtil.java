package com.exercice.cdi.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;


import java.util.Date;

@Component
public class JwtUtil {

  @Value("${jwt.secret}")
  private String secretKey;

  public String generateToken(String email) {
    long expirationTime = 1000 * 60 * 60 * 10; // 10 hours

    return Jwts.builder()
        .setSubject(email)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();
  }

  public String extractEmail(String token) {
    return getClaims(token).getSubject(); // subject is typically the email
  }

  private Claims getClaims(String token) {
    return Jwts.parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(token)
        .getBody();
  }
}
