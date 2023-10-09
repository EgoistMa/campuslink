package com.example.campuslink.Form;

import com.example.campuslink.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.UUID;

import static com.example.campuslink.utils.InputCheck.isNull;

public class RegisterForm {
    @ApiModelProperty(value = "firstName",required = true,example = "alice")
    private String firstName;
    @ApiModelProperty(value = "middleName",required = true,example = "middle")
    private String middleName;
    @ApiModelProperty(value = "lastName",required = true,example = "lastName")
    private String lastName;
    @ApiModelProperty(value = "email",required = true,example = "alice@domin.com")
    private String email;
    @ApiModelProperty(value = "password",required = true,example = "alicepass")
    private String password;
    @ApiModelProperty(value = "nickName",required = true,example = "ali")
    private String nickName;
    @JsonIgnore
    private Date lastLoginTime;
    @JsonIgnore
    private String accountStatus;
    @ApiModelProperty(value = "googleToken",required = true,example = "googleToken")
    private String googleToken;
    @ApiModelProperty(value = "oktaToken",required = true,example = "oktaToken")
    private String oktaToken;

    public RegisterForm(String firstName, String middleName, String lastName, String email, String password, String nickName, String googleToken, String oktaToken) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.googleToken = googleToken;
        this.oktaToken = oktaToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public boolean formNotLegal() {
        return isNull(firstName) ||
                isNull(middleName) ||
                isNull(lastName) ||
                isNull(email) ||
                isNull(password) ||
                isNull(nickName) ||
                isNull(googleToken) ||
                isNull(oktaToken);
    }
    public User createUser(){
        //hash password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);
        return new User(UUID.randomUUID(),
                firstName,
                middleName,
                lastName,
                email,
                hashedPassword,
                nickName,
                new Date(),
                "active",
                googleToken,
                oktaToken);
    }
}
