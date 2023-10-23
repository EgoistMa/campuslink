package com.shiropure.campuslink.repository.customInterface;

import com.shiropure.campuslink.entity.Token;

import java.util.Optional;
import java.util.UUID;

public interface CustomTokenRepository {
    Optional<Token> findByToken(String token);

    public void disableByToken(String token);

    void disableByUuid(UUID uuid);
}
