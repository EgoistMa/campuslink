package com.example.campuslink.repository.impl;

import com.example.campuslink.entity.Token;
import com.example.campuslink.entity.User;
import com.example.campuslink.repository.CustomTokenRepository;
import com.example.campuslink.repository.CustomUserRepository;
import com.example.campuslink.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Optional;

public class CustomTokenRepositoryImpl implements CustomTokenRepository {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void disableTokenByToken(String token){
        Query query = new Query();
        query.addCriteria(Criteria.where("Token").is(token));
        Update update = new Update();
        update.set("isActive", false);
        mongoTemplate.updateFirst(query, update, Token.class);
    }

    @Override
    public Optional<Token> findTokenByToken(String token) {
        Query query = new Query();
        query.addCriteria(Criteria.where("Token").is(token));
        Token token1 = mongoTemplate.findOne(query, Token.class);
        if (token1 != null) {
            return Optional.of(token1);
        } else {
            return Optional.empty();
        }
    }
}
