package com.example.campuslink.repository;

import com.example.campuslink.entity.Token;

import java.util.Optional;

public interface CustomTokenRepository {
    void disableTokenByToken(String token);

    Optional<Token> findTokenByToken(String token);
}
