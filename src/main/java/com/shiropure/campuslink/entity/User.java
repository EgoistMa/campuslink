package com.shiropure.campuslink.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;
@Document(collection = "user")
public class User {
    @Id
    private UUID uuid;
    private String username;
    private String email;
    private String role;
    private String password;
    private String firstname;
    private String lastname;
    private Date lastLoginTime;
    private boolean isActive;

    public User() {
    }

    public User(UUID uuid, String username, String email, String role, String password, String firstname, String lastname, Date lastLoginTime, boolean isActive) {
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.lastLoginTime = lastLoginTime;
        this.isActive = isActive;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
