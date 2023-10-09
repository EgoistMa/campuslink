package com.example.campuslink.Form;

import io.swagger.annotations.ApiModelProperty;

import static com.example.campuslink.utils.InputCheck.isNull;

public class LoginForm {
    @ApiModelProperty(value = "email",required = true,example = "alice@domin.com")
    private String email;
    @ApiModelProperty(value = "password",required = true,example = "alicepass")
    private String password;

    public LoginForm(String email, String password) {
        this.email = email;
        this.password = password;
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


    public boolean formNotLegal() {
        return (isNull(email) || isNull(password));
    }
}
