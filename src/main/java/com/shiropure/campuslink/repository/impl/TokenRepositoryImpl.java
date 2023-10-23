package com.shiropure.campuslink.repository.impl;

import com.shiropure.campuslink.entity.Token;
import com.shiropure.campuslink.repository.customInterface.CustomTokenRepository;
import com.shiropure.campuslink.utils.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public void disableByToken(String token){
        Query query = new Query();
        query.addCriteria(Criteria.where("Token").is(token));
        Update update = new Update();
        update.set("isActive", false);
        mongoTemplate.updateFirst(query, update, Token.class);
    }

    @Override
    public void disableByUuid(UUID uuid) {
        // 从数据库中获取所有Token
        List<Token> tokens = mongoTemplate.findAll(Token.class);
        for (Token token : tokens) {
            try {
                // 尝试将token字符串转换为UUID
                String tokenUuid = TokenGenerator.getUUIDFromToken(token.getToken()).get();

                // 检查转换后的UUID是否与参数匹配
                if (tokenUuid.equals(uuid.toString())) {
                    Query query = new Query(Criteria.where("token").is(token.getToken()));
                    Update update = new Update().set("isActive", false);
                    mongoTemplate.updateFirst(query, update, Token.class);
                }
            } catch (Exception e) {
                // 如果转换失败，跳过
                continue;
            }
        }
    }
}
