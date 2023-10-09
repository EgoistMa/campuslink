package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Token")
public class Token {
    private String token;
    private Date expiryDate;

    private boolean isActive;

    public Token(String token, Date expiryDate) {
        this.token = token;
        this.expiryDate = expiryDate;
        isActive = true;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Token{" +
                "Token='" + token + '\'' +
                ", ExpiryDate=" + expiryDate +
                ", isActive=" + isActive +
                '}';
    }
}
