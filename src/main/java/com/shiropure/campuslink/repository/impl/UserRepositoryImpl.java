package com.shiropure.campuslink.repository.impl;

import com.shiropure.campuslink.entity.User;
import com.shiropure.campuslink.repository.customInterface.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

public class UserRepositoryImpl implements CustomUserRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Optional<User> findUserByUuid(UUID uuid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("uuid").is(uuid));
        User User = mongoTemplate.findOne(query, User.class);

        return Optional.ofNullable(User);
    }
    @Override
    public Optional<User> findUserByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        User User = mongoTemplate.findOne(query, User.class);

        return Optional.ofNullable(User);
    }

    public boolean userAlreadyExists(String email) {
        return findUserByEmail(email).isPresent();
    }

    @Override
    public Optional<User> login(String email, String password) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        User user = mongoTemplate.findOne(query, User.class);
        if (user != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if(encoder.matches(password, user.getPassword())){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

}
