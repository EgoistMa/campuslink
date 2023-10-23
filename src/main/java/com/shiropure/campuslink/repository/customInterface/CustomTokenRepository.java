package com.shiropure.campuslink.repository.customInterface;

import com.shiropure.campuslink.entity.Token;

import java.util.Optional;

public interface CustomTokenRepository {
    Optional<Token> findByToken(String token);
    boolean TokenIsAvailable(String token);
}
