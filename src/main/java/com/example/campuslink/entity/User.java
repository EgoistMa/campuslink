package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "users")
public class User {
    private UUID uuid;
    private String firstname;
    private String middleName;
    private String lastname;
    private String email;
    private String password;
    private String nickname;
    private Date lastLoginTime;
    private String accountStatus;
    private String googleToken;
    private String oktaToken;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return uuid == user.uuid && firstname.equals(user.firstname) && Objects.equals(middleName, user.middleName) && lastname.equals(user.lastname) && email.equals(user.email) && password.equals(user.password) && nickname.equals(user.nickname) && lastLoginTime.equals(user.lastLoginTime) && accountStatus.equals(user.accountStatus) && Objects.equals(googleToken, user.googleToken) && Objects.equals(oktaToken, user.oktaToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, firstname, middleName, lastname, email, password, nickname, lastLoginTime, accountStatus, googleToken, oktaToken);
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + uuid +
                ", firstname='" + firstname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", googleToken='" + googleToken + '\'' +
                ", oktaToken='" + oktaToken + '\'' +
                '}';
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getGoogleToken() {
        return googleToken;
    }

    public void setGoogleToken(String googleToken) {
        this.googleToken = googleToken;
    }

    public String getOktaToken() {
        return oktaToken;
    }

    public void setOktaToken(String oktaToken) {
        this.oktaToken = oktaToken;
    }

    public User(UUID uuid, String firstname, String middleName, String lastname, String email, String password, String nickname, Date lastLogintime, String accountstatus, String googletoken, String oktatoken) {
        this.uuid = uuid;
        this.firstname = firstname;
        this.middleName = middleName;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.lastLoginTime = lastLogintime;
        this.accountStatus = accountstatus;
        this.googleToken = googletoken;
        this.oktaToken = oktatoken;
    }
    public User(){

    }
}
