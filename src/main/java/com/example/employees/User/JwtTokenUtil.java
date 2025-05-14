package com.example.employees.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil {

    // ✅ Secure, strong key — should be at least 32 characters
    private static final String SECRET_KEY = "this_is_a_very_long_secret_key_1234567890!";

    // Use a Key object instead of raw String
    private static final Key SIGNING_KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour in milliseconds

    /**
     * Generate a JWT token using the UserEntity's username.
     */
    public String generateToken(UserEntity user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SIGNING_KEY, SignatureAlgorithm.HS256) // 🛡 Secure signing
                .compact();
    }

    /**
     * Extract username from the JWT token.
     */
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Helper method to parse JWT and extract all claims.
     */
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY) // Use Key object here too
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
