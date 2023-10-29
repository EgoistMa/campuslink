package com.shiropure.campuslink.repository;

import com.shiropure.campuslink.entity.PassResetToken;
import com.shiropure.campuslink.entity.Setting;
import com.shiropure.campuslink.repository.customInterface.CustomSettingRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SettingRepository extends MongoRepository<Setting, String>, CustomSettingRepository {

}
