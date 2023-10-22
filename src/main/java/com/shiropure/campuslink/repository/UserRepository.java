package com.shiropure.campuslink.repository;

import com.shiropure.campuslink.entity.User;
import com.shiropure.campuslink.repository.customInterface.CustomUserRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository {
}
