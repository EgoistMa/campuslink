package com.shiropure.campuslink.repository;

import com.shiropure.campuslink.entity.Token;
import com.shiropure.campuslink.repository.customInterface.CustomTokenRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepository extends MongoRepository<Token, String>, CustomTokenRepository {
}
