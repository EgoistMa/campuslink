package com.example.campuslink.repository;

import com.example.campuslink.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface CustomUserRepository {
    Optional<UUID> login(String username, String password);

    Optional<User> findUserByEmail(String email);
}
