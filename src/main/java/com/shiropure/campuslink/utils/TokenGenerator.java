package com.shiropure.campuslink.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class TokenGenerator {
    public static String generatorToken(UUID userId){
        String token = Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "8A9h8jceQsw2xDl/gyHecnhYWdxLQ+nOzfVXCik1OF8=")
                .compact();
        return token;
    }
    public static Optional<String> getUUIDFromToken(String token)
    {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("8A9h8jceQsw2xDl/gyHecnhYWdxLQ+nOzfVXCik1OF8=")
                    .parseClaimsJws(token)
                    .getBody();
            return Optional.of(claims.getSubject());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
