package com.shiropure.campuslink.repository.impl;

import com.shiropure.campuslink.entity.TimeTable;
import com.shiropure.campuslink.entity.Token;
import com.shiropure.campuslink.repository.customInterface.CustomTimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.UUID;

public class TimeTableRepositoryImpl implements CustomTimeTableRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public TimeTable findByOwnerUUID(UUID uuid){
        Query query = new Query();
        query.addCriteria(Criteria.where("owner").is(uuid));
        return mongoTemplate.findOne(query, TimeTable.class);
    }
}
