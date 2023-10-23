package com.shiropure.campuslink.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "passResetToken")
public class PassResetToken {
    @Id

    private String token;
    private Date expireDate;

    private boolean isActive;
    public PassResetToken() {
    }
    public PassResetToken(String token, Date expireDate) {
        this.token = token;
        this.expireDate = expireDate;
        this.isActive = true;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

