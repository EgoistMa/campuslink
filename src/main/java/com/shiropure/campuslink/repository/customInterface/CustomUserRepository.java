package com.shiropure.campuslink.repository.customInterface;

import com.shiropure.campuslink.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface CustomUserRepository {

    Optional<User> findUserByEmail(String email);

    public boolean userAlreadyExists(String email);

    public Optional<User> findUserByUuid(UUID uuid);

    Optional<User> login(String username, String password);

}
