package com.shiropure.campuslink.repository;

import com.shiropure.campuslink.entity.Log;
import com.shiropure.campuslink.repository.customInterface.CustomLogRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String>, CustomLogRepository {
}

