package com.shiropure.campuslink.repository.impl;

import com.shiropure.campuslink.entity.Setting;
import com.shiropure.campuslink.entity.TimeTable;
import com.shiropure.campuslink.repository.customInterface.CustomSettingRepository;
import com.shiropure.campuslink.repository.customInterface.CustomTimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Optional;
import java.util.UUID;

public class SettingRepositoryImpl implements CustomSettingRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Optional<Setting> findByUUid(String uuid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("owner").is(UUID.fromString(uuid)));
        return Optional.ofNullable(mongoTemplate.findOne(query, Setting.class));
    }
}
