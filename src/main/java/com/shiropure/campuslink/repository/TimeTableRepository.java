package com.shiropure.campuslink.repository;

import com.shiropure.campuslink.entity.TimeTable;
import com.shiropure.campuslink.entity.Token;
import com.shiropure.campuslink.repository.customInterface.CustomTimeTableRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimeTableRepository extends MongoRepository<TimeTable, String>, CustomTimeTableRepository {

}
