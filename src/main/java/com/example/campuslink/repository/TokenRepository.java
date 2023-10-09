package com.example.campuslink.repository;

import com.example.campuslink.entity.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepository extends MongoRepository<Token, String>, CustomTokenRepository{
}
