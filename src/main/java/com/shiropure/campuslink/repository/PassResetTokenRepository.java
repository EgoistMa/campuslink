package com.shiropure.campuslink.repository;

import com.shiropure.campuslink.entity.PassResetToken;
import com.shiropure.campuslink.repository.customInterface.CustomPassResetTokenRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PassResetTokenRepository extends MongoRepository<PassResetToken, String>, CustomPassResetTokenRepository {
}

