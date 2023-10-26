package com.shiropure.campuslink.repository;

import com.shiropure.campuslink.entity.UserAssets;
import com.shiropure.campuslink.repository.customInterface.CustomUserAssetsRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAssetsRepository extends MongoRepository<UserAssets, String>,CustomUserAssetsRepository{
}
