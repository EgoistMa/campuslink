package com.shiropure.campuslink.repository.impl;

import com.shiropure.campuslink.entity.UserAssets;
import com.shiropure.campuslink.repository.customInterface.CustomUserAssetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.UUID;

public class UserAssetsRepositoryImpl implements CustomUserAssetsRepository {
    @Autowired
    private MongoTemplate mongoTemplate;
    public UserAssets getUserAssetsFromUuid(UUID uuid){
        Query query = new Query();
        query.addCriteria(Criteria.where("user").is(uuid));
        return mongoTemplate.findOne(query, UserAssets.class);
    }
}
