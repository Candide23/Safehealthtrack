package com.Safehealthtrack.utils;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.util.Date;


//This JwtUtil class provides utility methods for handling JSON Web Tokens (JWTs):
//generateToken: Creates a JWT with the username as the subject, valid for 1 hour.
//extractUsername: Extracts the username (subject) from a given token.
// validateToken: Validates a token by checking its signature and expiration.

@Component
public class JwtUtil {
    private final String secret =  "healthTrack_secret";
    private final long expirationMs = 3600000;

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJwt(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

}
