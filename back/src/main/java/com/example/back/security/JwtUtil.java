package com.example.back.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private final static String SECRET_KEY = "4qhq8LrEBfYcaRHxhdb9zURb2Rf8e7UdRHb2xhdbrEBhdb9zUqhq8Le7UdRHhq4qhq8Lr";
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;

    public String extractUserName(String token) {
        return extractClaimUsername(token);
    }
    public String extractClaimUsername(String token){
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }
    public Date extractExpiration(String token) {
        return extractClaimDate(token);
    }
    public Date extractClaimDate(String token){
        Claims claims = extractAllClaims(token);
        return (Date) claims.getExpiration();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(token).getBody();
    }
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256).compact();
    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}

