package com.shiropure.campuslink.repository.impl;

import com.shiropure.campuslink.entity.Token;
import com.shiropure.campuslink.repository.customInterface.CustomTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Optional;

public class TokenRepositoryImpl implements CustomTokenRepository {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public Optional<Token> findByToken(String token) {
        Query query = new Query();
        query.addCriteria(Criteria.where("Token").is(token));
        Token token1 = mongoTemplate.findOne(query, Token.class);
        if (token1 != null) {
            return Optional.of(token1);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean TokenIsAvailable(String token){
        Query query = new Query();
        query.addCriteria(Criteria.where("Token").is(token));
        Token token1 = mongoTemplate.findOne(query, Token.class);
        if (token1 != null) {
            return token1.isActive();
        } else {
            return false;
        }
    }
}
