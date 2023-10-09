package com.example.campuslink.utils;

import com.example.campuslink.entity.Token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
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
}
