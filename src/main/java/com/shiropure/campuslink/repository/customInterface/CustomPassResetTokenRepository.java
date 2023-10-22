package com.shiropure.campuslink.repository.customInterface;

import com.shiropure.campuslink.entity.PassResetToken;

import java.util.Optional;

public interface CustomPassResetTokenRepository {
    Optional<PassResetToken> findByToken(String token);
}
